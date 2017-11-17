package com.onyas.sftp;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.DisconnectReason;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.xfer.FileSystemFile;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * https://github.com/hierynomus/sshj/issues/360
 *
 */
public class SshjMultiThread {
    private static final String HOST = "localhost";
    private static final int PORT = 2022;

    private static ConcurrentHashMap<Integer, SFTPClient> clients = new ConcurrentHashMap();
    static ExecutorService sftpThreadPool = Executors.newFixedThreadPool(3);

    private SFTPClient getClient(int userId, String userName, String pwd) throws Exception {
        if (clients.contains(userId)) {
            return clients.get(userId);
        }
        SSHClient ssh = new SSHClient();
        try {
            ssh.connect(HOST, PORT);
        } catch (TransportException e) {
            if (e.getDisconnectReason() == DisconnectReason.HOST_KEY_NOT_VERIFIABLE) {
                String msg = e.getMessage();
                String[] split = msg.split("`");
                String vc = split[3];
                ssh = new SSHClient();
                ssh.addHostKeyVerifier(vc);
                ssh.setTimeout(3000);
                ssh.connect(HOST, PORT);
            } else {
                throw e;
            }
        }
        ssh.authPassword(userName, pwd);
        final SFTPClient sftp = ssh.newSFTPClient();
        clients.put(userId, sftp);
        return sftp;
    }

    static class UploadThread implements Runnable {
        private int userId;
        private String userName;
        private String pwd;
        private String file;

        public UploadThread(int userId, String userName, String pwd, String file) {
            this.userId = userId;
            this.userName = userName;
            this.pwd = pwd;
            this.file = file;
        }

        @Override
        public void run() {
            try {
                String fileName = file.substring(file.lastIndexOf("/"));
                SFTPClient sftp = new SshjMultiThread().getClient(userId, userName, pwd);
                sftp.mkdirs("/upload/var/log/dropbox/" + userId);
                sftp.put(new FileSystemFile(file), "/upload/var/log/dropbox/" + userId + fileName);
            } catch (Exception e) {
                System.out.print("userId " + userId + " file " + file);
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
            throws IOException {
        final String src = "/var/log/dropbox/dropbox.log";
        final String src2 = "/Users/edward.zheng/Downloads/rc_user.csv";
        for (int i = 0; i < 5; i++) {
            sftpThreadPool.submit(new UploadThread(i, "foo", "pass", src));
        }
        for (int i = 0; i < 5; i++) {
            sftpThreadPool.submit(new UploadThread(i, "foo", "pass", src2));
        }
    }

}

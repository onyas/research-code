package com.onyas.sftp;

import com.jcraft.jsch.*;

public class JcraftSftpConnect {

    public static String USERNAME = "foo";
    public static String PASSWORD = "pass";
    public static String HOST = "localhost";
    public static int PORT = 2022;
    public static String targetDir = "upload";

    public static void main(String[] args) throws JSchException, SftpException {
        JSch jsch = new JSch();
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");

        Session session = jsch.getSession(USERNAME, HOST, PORT);
        session.setConfig(config);
        session.setPassword(PASSWORD);
        session.connect(15000);
        session.setServerAliveInterval(5000);
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");

        channelSftp.connect(15000);
        channelSftp.cd(channelSftp.getHome());
        SftpATTRS attrs = channelSftp.lstat(targetDir);
        System.out.println(attrs);
        channelSftp.cd(targetDir);

        channelSftp.put("/var/log/dropbox/dropbox.log","/upload/dropbox1");

        channelSftp.disconnect();
        session.disconnect();
    }


}

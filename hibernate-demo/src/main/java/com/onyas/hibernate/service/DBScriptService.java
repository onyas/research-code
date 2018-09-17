package com.onyas.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@Service
public class DBScriptService {

    @Autowired
    private DataSource dataSource;

    public void executeSql(String sql) throws SQLException {
        InputStreamResource sqlStreamResource = new InputStreamResource(new ByteArrayInputStream(sql.getBytes(StandardCharsets.UTF_8)));
        ScriptUtils.executeSqlScript(dataSource.getConnection(), sqlStreamResource);
    }
}

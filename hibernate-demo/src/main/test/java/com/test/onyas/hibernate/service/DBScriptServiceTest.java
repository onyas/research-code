package com.test.onyas.hibernate.service;

import com.onyas.hibernate.service.DBScriptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.Random;

@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DBScriptServiceTest {

    @Autowired
    private DBScriptService dbScriptService;

    @Test
    public void testExecuteSql() throws SQLException {
        String insertSql = "INSERT INTO \"public\".\"test_user\"(\"accesstoken\", \"ownerid\", \"refreshtoken\", \"threadname\", \"username\") VALUES('test232', " + new Random().nextInt() + ", '33', '232', '123123') RETURNING \"id\", \"accesstoken\", \"ownerid\", \"refreshtoken\", \"threadname\", \"username\";\n";
        String update = "update test_user set username='123' where id = (select max(id) from test_user);\n";
        dbScriptService.executeSql(insertSql + update);
    }

}

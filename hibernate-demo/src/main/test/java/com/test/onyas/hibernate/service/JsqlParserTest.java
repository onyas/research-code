package com.test.onyas.hibernate.service;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.WithItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JsqlParserTest {


    @Test
    public void testSelect() throws JSQLParserException {
        Statement stmt = CCJSqlParserUtil.parse("SELECT * FROM user");
        Select selectStatement = (Select) stmt;
        List<WithItem> list = selectStatement.getWithItemsList();
        for (WithItem item : list) {
            System.out.println(item);
        }
    }

}

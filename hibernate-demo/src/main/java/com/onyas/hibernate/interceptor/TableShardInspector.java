package com.onyas.hibernate.interceptor;

import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.springframework.util.StringUtils;

public class TableShardInspector implements StatementInspector {

    private String srcName = "";//源表名
    private String destName = ""; // 目标表名

    public TableShardInspector() {
    }

    public TableShardInspector(String srcName, String destName) {
        this.srcName = srcName;
        this.destName = destName;
    }


    @Override
    public String inspect(String sql) {
        if (StringUtils.isEmpty(srcName) || StringUtils.isEmpty(srcName)) {
            return sql;
        }
        sql = sql.replaceAll(srcName, destName);
        System.out.println(Thread.currentThread().getName() + " on inspect sql :" + sql);
        return sql;
    }

}

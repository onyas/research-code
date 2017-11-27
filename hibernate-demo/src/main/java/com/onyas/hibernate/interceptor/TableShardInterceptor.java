package com.onyas.hibernate.interceptor;

import org.hibernate.EmptyInterceptor;
import org.springframework.util.StringUtils;

public class TableShardInterceptor extends EmptyInterceptor {

    private String srcName = "";//源表名
    private String destName = ""; // 目标表名

    public TableShardInterceptor() {
    }

    public TableShardInterceptor(String srcName, String destName) {
        this.srcName = srcName;
        this.destName = destName;
    }

    public String onPrepareStatement(String sql) {
        if (StringUtils.isEmpty(srcName) || StringUtils.isEmpty(srcName)) {
            return sql;
        }
        sql = sql.replaceAll(srcName, destName);
        System.out.println("onPrepareStatement sql :" + sql);
        return sql;
    }
}

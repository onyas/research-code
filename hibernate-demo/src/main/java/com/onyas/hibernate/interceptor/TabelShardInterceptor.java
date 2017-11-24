package com.onyas.hibernate.interceptor;

import org.hibernate.EmptyInterceptor;

public class TabelShardInterceptor  extends EmptyInterceptor{

    @Override
    public String onPrepareStatement(String sql) {
        System.out.printf("onPrepareStatement: " + sql);
        return super.onPrepareStatement(sql);
    }
}

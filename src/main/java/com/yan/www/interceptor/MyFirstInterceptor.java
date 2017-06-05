package com.yan.www.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Administrator
 * Date: 2017/6/5
 * Time: 17:24
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class MyFirstInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.err.println("MyFirstInterceptor intercept: " + invocation.getMethod());
        Object proceed = invocation.proceed();
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        System.err.println("MyFirstInterceptor plugin: " + target);
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {
        System.err.println("MyFirstInterceptor setProperties: " + properties);
    }
}

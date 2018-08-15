package com.yan.www.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static Logger logger = LoggerFactory.getLogger(MyFirstInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("intercept: " + invocation.getMethod());
        Object proceed = invocation.proceed();
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        logger.info("plugin: " + target);
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {
        logger.info("setProperties: " + properties);
    }
}

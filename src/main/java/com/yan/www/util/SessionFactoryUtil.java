package com.yan.www.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Administrator
 * Date: 2017/5/17
 * Time: 14:54
 */
public class SessionFactoryUtil {

    private static final String CONFIG_NAME = "mybatis-config.xml";
    private static volatile SqlSessionFactory instance;

    public static SqlSessionFactory getInstance() {
        if (instance == null) {
            synchronized (SqlSessionFactory.class) {
                if (instance == null) {
                    try {
                        InputStream inputStream = Resources.getResourceAsStream(CONFIG_NAME);
                        instance = new SqlSessionFactoryBuilder().build(inputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return instance;
    }
}

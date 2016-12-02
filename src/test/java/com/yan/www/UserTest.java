package com.yan.www;

import com.yan.www.mapper.UserMapper;
import com.yan.www.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;


public class UserTest {
    private static Logger logger = Logger.getLogger(App.class.getSimpleName());

    @Test
    public void testUserMapper() {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();

            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = userMapper.queryById(1);

            logger.info(user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

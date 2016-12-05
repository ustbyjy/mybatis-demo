package com.yan.www;

import com.yan.www.mapper.BlogMapper;
import com.yan.www.model.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class BlogTest {
    private static Logger logger = org.apache.log4j.Logger.getLogger(BlogTest.class);

    @Test
    public void testBlogMapper() {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();

            BlogMapper blogMapper = session.getMapper(BlogMapper.class);
//            Blog blog = blogMapper.selectBlog(1);
            Blog blog = blogMapper.queryById(1);
            logger.info(blog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.yan.www;

import com.yan.www.mapper.BlogMapper;
import com.yan.www.model.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;


public class BlogTest {
    private static Logger logger = Logger.getLogger(BlogTest.class.getSimpleName());

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
            logger.info(blog.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

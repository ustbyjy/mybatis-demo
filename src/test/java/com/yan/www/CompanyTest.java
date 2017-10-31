package com.yan.www;

import com.yan.www.mapper.CompanyMapper;
import com.yan.www.model.Company;
import com.yan.www.model.CompanyExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;


public class CompanyTest {
    private static Logger logger = Logger.getLogger(CompanyTest.class);

    @Test
    public void testCompanyMapper() {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();

            CompanyMapper companyMapper = session.getMapper(CompanyMapper.class);
            List<Company> companyList = companyMapper.selectByExample(null);
            System.out.println(companyList.size());

            CompanyExample example = new CompanyExample();
            CompanyExample.Criteria andCriteria = example.createCriteria();
            andCriteria.andIdGreaterThan(1L).andNameLike("%阿里%");

            CompanyExample.Criteria orCriteria = example.createCriteria();
            orCriteria.andAddressIn(Arrays.asList("深圳"));
            example.or(orCriteria);

            companyList = companyMapper.selectByExample(example);
            System.out.println(companyList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

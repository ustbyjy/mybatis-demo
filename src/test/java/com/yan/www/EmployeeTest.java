package com.yan.www;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yan.www.mapper.EmployeeMapper;
import com.yan.www.mapper.EmployeeMapperDynamicSQL;
import com.yan.www.mapper.EmployeeMapperPlus;
import com.yan.www.model.Employee;
import com.yan.www.util.SessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.*;

public class EmployeeTest {
    private static Logger logger = Logger.getLogger(EmployeeTest.class);

    @Test
    public void testEmployeeMapper() {

        SqlSession session = SessionFactoryUtil.getInstance().openSession(true);
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        Employee employee = new Employee(null, "张三", "zhangsan@163.com", "Male");
        employeeMapper.addEmp(employee);
        logger.info(employee);

        employee.setEmail("zhangsan@qq.com");
        employeeMapper.updateEmp(employee);
        employee = employeeMapper.getEmpById(employee.getId());
        logger.info(employee);

        Integer employeeId = employee.getId();
        employeeMapper.deleteEmpById(employeeId);
        employee = employeeMapper.getEmpById(employeeId);
        logger.info(employee);

        session.close();
    }

    @Test
    public void testEmployeeMapper2() {

        SqlSession session = SessionFactoryUtil.getInstance().openSession(true);
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.getEmpByIdAndLastName(2, "张三");
        logger.info(employee);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", 2);
        params.put("lastName", "张三");
        employee = employeeMapper.getEmpByMap(params);
        logger.info(employee);

        session.close();
    }

    @Test
    public void testEmployeeMapper3() {

        SqlSession session = SessionFactoryUtil.getInstance().openSession(true);
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        List<Employee> employeeList = employeeMapper.getEmpsByLastNameLike("%张%");
        logger.info(employeeList == null ? "null" : employeeList.size());
        logger.info(employeeList);

        session.close();
    }

    @Test
    public void testEmployeeMapper4() {

        SqlSession session = SessionFactoryUtil.getInstance().openSession(true);
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Map<String, Object> map = employeeMapper.getEmpByIdReturnMap(2);
        logger.info(map);

        session.close();
    }

    @Test
    public void testEmployeeMapper5() {

        SqlSession session = SessionFactoryUtil.getInstance().openSession(true);
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Map<Integer, Employee> map = employeeMapper.getEmpsByLastNameLikeReturnMap("%张%");
        logger.info(map);

        session.close();
    }

    @Test
    public void testEmployeeMapper6() {

        SqlSession session = SessionFactoryUtil.getInstance().openSession(true);
        EmployeeMapperPlus employeeMapperPlus = session.getMapper(EmployeeMapperPlus.class);
        Employee employee = employeeMapperPlus.getEmpById(2);
        logger.info(employee);

        session.close();
    }

    @Test
    public void testEmployeeMapper7() {

        SqlSession session = SessionFactoryUtil.getInstance().openSession(true);
        EmployeeMapperPlus employeeMapperPlus = session.getMapper(EmployeeMapperPlus.class);
        Employee employee = employeeMapperPlus.getEmpAndDept(2);
        logger.info(employee);

        session.close();
    }

    @Test
    public void testEmployeeMapper8() {

        SqlSession session = SessionFactoryUtil.getInstance().openSession(true);
        EmployeeMapperDynamicSQL employeeMapperDynamicSQL = session.getMapper(EmployeeMapperDynamicSQL.class);
        Employee employee = new Employee(1, "Admin", null, null);
        employeeMapperDynamicSQL.updateEmp(employee);
        logger.info(employee);

        session.close();
    }

    @Test
    public void testEmployeeMapper9() {

        SqlSession session = SessionFactoryUtil.getInstance().openSession(true);
        EmployeeMapperDynamicSQL employeeMapperDynamicSQL = session.getMapper(EmployeeMapperDynamicSQL.class);
        List<Employee> employeeList = employeeMapperDynamicSQL.getEmpsConditionForeach(Arrays.asList(1, 2, 3, 4));
        logger.info(employeeList);

        session.close();
    }

    /**
     * 只有一级缓存的会话提交或者关闭了，才会放到二级缓存里。
     */
    @Test
    public void testEmployeeMapper10() {
        SqlSession session1 = SessionFactoryUtil.getInstance().openSession();
        SqlSession session2 = SessionFactoryUtil.getInstance().openSession();
        SqlSession session3 = SessionFactoryUtil.getInstance().openSession();

        EmployeeMapper employeeMapper1 = session1.getMapper(EmployeeMapper.class);
        EmployeeMapper employeeMapper2 = session2.getMapper(EmployeeMapper.class);
        EmployeeMapper employeeMapper3 = session3.getMapper(EmployeeMapper.class);

        Employee employee1 = employeeMapper1.getEmpById(2);
        logger.info(employee1);
        session1.close();

        Employee employee2 = employeeMapper2.getEmpById(2);
        logger.info(employee2);
        session2.close();

        Employee employee3 = employeeMapper3.getEmpById(2);
        logger.info(employee3);
        session3.close();
    }

    @Test
    public void testPageHelper() {
        SqlSession session = SessionFactoryUtil.getInstance().openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Page<Employee> page = PageHelper.startPage(1, 5);
            List<Employee> emps = mapper.getEmps();
            PageInfo<Employee> pageInfo = new PageInfo<Employee>(emps);
            for (Employee employee : emps) {
                System.out.println(employee);
            }
            System.err.println(page);
            System.err.println(pageInfo);
        } finally {
            session.close();
        }
    }

    @Test
    public void testInsert() {
        SqlSession session = SessionFactoryUtil.getInstance().openSession(false);
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            for (int i = 0; i < 20; i++) {
                Employee employee = new Employee();
                int number = Math.abs(new Random().nextInt());
                employee.setLastName("用户" + number);
                String[] genders = {"Male", "Female"};
                employee.setGender(genders[new Random().nextInt(2)]);
                employee.setEmail("user" + number + "@zulong.com");

                mapper.addEmp(employee);
                System.out.println("id: " + employee.getId());
            }
            session.flushStatements();
            session.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void testBatch() {
        SqlSession session = SessionFactoryUtil.getInstance().openSession();
//        SqlSession session = SessionFactoryUtil.getInstance().openSession(ExecutorType.BATCH);
        long start = System.currentTimeMillis();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            for (int i = 0; i < 10000; i++) {
                Employee employee = new Employee();
                int number = Math.abs(new Random().nextInt());
                employee.setLastName("用户" + number);
                String[] genders = {"Male", "Female"};
                employee.setGender(genders[new Random().nextInt(2)]);
                employee.setEmail("user" + number + "@zulong.com");

                mapper.addEmp(employee);
            }
            session.commit();
            long end = System.currentTimeMillis();
            System.err.println("Time:" + (end - start));
        } finally {
            session.close();
        }
    }
}

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
//        employeeMapper.addEmpWithId(employee, 1);
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
    public void testEmployeeMapper11() {

        SqlSession session = SessionFactoryUtil.getInstance().openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        Employee employee1 = new Employee(1, "A1", "A1@163.com", "Male");
        Employee employee2 = new Employee(2, "A2", "A2@163.com", "Male");
        Employee employee3 = new Employee(3, "A3", "A3@163.com", "Female");
        Integer addResult1 = employeeMapper.addEmpWithId(employee1, 1);
        Integer addResult2 = employeeMapper.addEmpWithId(employee2, 2);
        Integer addResult3 = employeeMapper.addEmpWithId(employee3, 3);
        logger.info("addResult1=" + addResult1);
        logger.info("addResult2=" + addResult2);
        logger.info("addResult3=" + addResult3);

        employee1.setLastName("B1");
        employee2.setGender("Female");
        employee3.setEmail("A3@qq.com");
        List<Employee> empList = new ArrayList<>();
        empList.add(employee1);
        empList.add(employee2);
        empList.add(employee3);
        Integer updateResult1 = employeeMapper.updateEmpBatch(empList);
        logger.info("updateResult1=" + updateResult1);

        session.commit();
        session.close();
    }

    @Test
    public void testEmployeeMapper12() {

        SqlSession session = SessionFactoryUtil.getInstance().openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        Employee employee1 = new Employee(1, "A1", "A1@163.com", "Male");
        Employee employee2 = new Employee(2, "A2", "A2@163.com", "Male");
        Employee employee3 = new Employee(3, "A3", "A3@163.com", "Female");

        List<Employee> empList = new ArrayList<>();
        empList.add(employee1);
        empList.add(employee2);
        empList.add(employee3);
        Integer addBatchResult = employeeMapper.addEmpBatch(empList);
        logger.info("addBatchResult=" + addBatchResult);

        session.commit();
        session.close();
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

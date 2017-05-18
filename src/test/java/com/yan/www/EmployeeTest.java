package com.yan.www;

import com.yan.www.mapper.EmployeeMapper;
import com.yan.www.mapper.EmployeeMapperPlus;
import com.yan.www.model.Employee;
import com.yan.www.util.SessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}

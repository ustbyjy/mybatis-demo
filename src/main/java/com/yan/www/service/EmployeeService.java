package com.yan.www.service;

import com.yan.www.mapper.EmployeeMapper;
import com.yan.www.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Administrator
 * Date: 2017/5/31
 * Time: 17:25
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> getEmps() {
        return employeeMapper.getEmps();
    }
}

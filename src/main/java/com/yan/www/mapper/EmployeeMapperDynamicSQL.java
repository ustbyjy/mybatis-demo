package com.yan.www.mapper;

import com.yan.www.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Administrator
 * Date: 2017/5/25
 * Time: 17:00
 */
public interface EmployeeMapperDynamicSQL {

    void updateEmp(Employee employee);

    List<Employee> getEmpsConditionForeach(@Param("ids") List<Integer> ids);
}

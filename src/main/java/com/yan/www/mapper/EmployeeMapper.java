package com.yan.www.mapper;

import com.yan.www.model.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Administrator
 * Date: 2017/5/17
 * Time: 14:44
 */
@Repository
public interface EmployeeMapper {

    /**
     * 入参有多个参数时，需要使用注解@Param指定每个参数的name，否则对应key分别是param1、param2...
     *
     * @param id
     * @param lastName
     * @return
     */
    Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    Employee getEmpByMap(Map<String, Object> params);

    List<Employee> getEmpsByLastNameLike(String lastName);

    @MapKey("id")
    Map<Integer, Employee> getEmpsByLastNameLikeReturnMap(String lastName);

    Map<String, Object> getEmpByIdReturnMap(Integer id);

    Employee getEmpById(Integer id);

    Integer addEmp(Employee employee);

    Integer addEmpWithId(@Param("employee") Employee employee, @Param("id") Integer id);

    boolean updateEmp(Employee employee);

    void deleteEmpById(Integer id);

    List<Employee> getEmps();
}

package com.yan.www.controller;

import com.yan.www.model.Employee;
import com.yan.www.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Administrator
 * Date: 2017/5/31
 * Time: 17:23
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/getEmps")
    public String emps(Model model) {
        List<Employee> emps = employeeService.getEmps();
        model.addAttribute("allEmps", emps);
        return "list";
    }
}

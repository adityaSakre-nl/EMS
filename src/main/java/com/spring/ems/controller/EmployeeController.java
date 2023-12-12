package com.spring.ems.controller;

import com.spring.ems.model.Employee;
import com.spring.ems.model.RespObject;
import com.spring.ems.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("emp")
public class EmployeeController {

    EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService)
    {
        this.employeeService = employeeService;
    }

    //Signup page for employees
    @PostMapping("signUp")
    public String SignUp(@RequestBody Employee employee)
    {
       return employeeService.insertEmployee(employee);
    }




}

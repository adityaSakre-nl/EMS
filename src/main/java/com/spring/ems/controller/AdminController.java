package com.spring.ems.controller;

import com.spring.ems.model.Admin;
import com.spring.ems.model.Employee;
import com.spring.ems.service.AdminService;
import com.spring.ems.service.EmployeeService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {

    AdminService adminService;
    EmployeeService employeeService;

    AdminController (AdminService adminService, EmployeeService employeeService)
    {
        this.adminService = adminService;
        this.employeeService = employeeService;
    }


    //SignUp page for Admin
    @PostMapping("signUp")
    public String signUp(@RequestBody Admin user)
    {
        return adminService.insertAdmin(user);
    }


    //Login page for Admin
    @PostMapping("login")
    public String login(@RequestBody Map<String,Object> map)
    {
        return adminService.adminLogin(map.get("email").toString(), map.get("password").toString());
    }

    //Get all employee details
    @GetMapping("getEmp/all")
    public String getAllEmployee()
    {
        return employeeService.getAllEmployees();
    }

    //get employee detail for employee id
    @GetMapping("getEmp/{id}")
    public String getEmpById(@PathVariable String id)
    {
        return employeeService.getEmployeeById(id);
    }

    //delete employee detail for employee id
    @GetMapping("delEmp/{id}")
    public String deleteEmp(@PathVariable String id)
    {
        return employeeService.deleteByEmployeeID(id);
    }

    //delete all employee
    @GetMapping("delEmp/all")
    public String deleteAllEmp()
    {
        return employeeService.deleteAllEmployees();
    }


    //Update Employee with employee id
    @PostMapping("updateEmp/{id}")
    public String updateEmp(@PathVariable String id, @RequestBody Employee employee)
    {
        return employeeService.updateEmployee(id,employee);
    }

}

package com.spring.ems.service;

import com.spring.ems.model.Employee;
import com.spring.ems.model.EmployeeRepository;
import com.spring.ems.model.RespObject;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TokenService tokenService;

    EmployeeService( EmployeeRepository employeeRepository, TokenService tokenService)
    {
        this.employeeRepository = employeeRepository;
        this.tokenService = tokenService;
    }

    //Login for Employees
    public String employeeLogin(String email, String password)
    {
        String message = "Login is successful";
        List<Employee> foundEmployees = employeeRepository.getUserByEmail(email);
        if (foundEmployees.isEmpty())
        {
            message =  "Authentication failed";
        }
        else if(!foundEmployees.get(0).getPassword().equals(password))
        {
            message = "Incorrect Password";
        }

        String data = "{Email :"+ foundEmployees.get(0).getEmail() +"}";

        String token = tokenService.createToken(foundEmployees.get(0).getEmail());


        var resp = new RespObject(message, data, token);


        return  resp.print();

    }



    ////////Crud operations

    //Get all employees
    public String getAllEmployees()
    {
        var unprotectedList =  employeeRepository.findAll();
        int count = unprotectedList.size();

        //Create array of records to display
        String array = "[ \t ";
        for (var emp : unprotectedList)
        {
            array += emp.printProtected() +", ";
        }
        array += "]";

        var resp = new RespObject();

        resp.message = count+ " Employee record found";
        resp.data = array;

        return resp.print();
    }


    //Get employee by ID
    public String getEmployeeById(String emp_id)
    {
        String message;
        var resp = new  RespObject();

        var opt_emp =  employeeRepository.getUserByEmpId(emp_id);
        if (opt_emp.isEmpty())
        {
          message = "Employee not found";
        }
        else
        {
            var emp =  opt_emp.get(0);
            message = "Employee record found";

            resp.data = emp.printProtected();

        }
        resp.message = message;
        return resp.print();
    }

    //Insert Employee
    public String insertEmployee(Employee employee)
    {
        employeeRepository.save(employee);
        String message = "Sign up successful";

        var resp  = new RespObject(message, employee.toString(), "");

        return  resp.print();
    }

    //Delete Employee by employee ID
    public String deleteByEmployeeID(String emp_id)
    {
        String message;
        var resp = new  RespObject();

        var opt_emp =  employeeRepository.getUserByEmpId(emp_id);
        if (opt_emp.isEmpty())
        {
            message = "Employee record not found";
        }
        else
        {
            message = "Record Successfully deleted";
            var deleteEmp = opt_emp.get(0);
            resp.data = deleteEmp.printProtected();
            employeeRepository.delete(deleteEmp);
        }

        resp.message = message;
        return resp.print();
    }

    //Delete All employees
    public String deleteAllEmployees()
    {
        var unprotectedList =  employeeRepository.findAll();
        int count = unprotectedList.size();

        String array = "[ \t ";
        for (var emp : unprotectedList)
        {
            array += emp.printProtected() +", ";
        }
        array += "]";

        var resp = new RespObject();

        employeeRepository.deleteAll();
        resp.message = count+ " Employee record deleted";
        resp.data = array;

        return resp.print();
    }

    //Update employee details
    //mode 1: employee,  2: admin
    public String updateEmployee(String emp_id, Employee employee)
    {

        String message;
        var resp = new  RespObject();

        var opt_emp =  employeeRepository.getUserByEmpId(emp_id);
        if (opt_emp.isEmpty())
        {
            message = "Employee not found";
        }
        else
        {

            var updateEmp = opt_emp.get(0);

            if (employee.getEmployeeName() != null && !employee.getEmployeeName().isEmpty())
            {
                updateEmp.setEmployeeName(employee.getEmployeeName());
            }

            if (employee.getRole() != null && !employee.getRole().isEmpty())
            {
                updateEmp.setRole(employee.getRole());
            }

//            if (mode == 1)
//            {
//                if (employee.getEmail() != null && !employee.getEmail().isEmpty())
//                {
//                    updateEmp.setEmail(employee.getEmail());
//                }
//
//                if (employee.getPassword() !=null && !employee.getPassword().isEmpty() )
//                {
//                    updateEmp.setPassword(employee.getPassword());
//                }
//            }

            employeeRepository.save(updateEmp);
            message = "Employee record details updated";
            resp.data =  updateEmp.printProtected();
        }

        resp.message = message;
        return  resp.print();
    }

}

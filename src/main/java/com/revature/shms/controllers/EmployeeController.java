package com.revature.shms.controllers;
import com.revature.shms.models.Employee;
import com.revature.shms.repositories.EmployeeRepository;
import com.revature.shms.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public Employee createNewEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @RequestMapping("/{employeeId}")
    public Optional<Employee> getEmployeeById(@PathVariable String employeeId){
        return employeeRepository.findByEmployeeID(Integer.parseInt(employeeId));
    }

}

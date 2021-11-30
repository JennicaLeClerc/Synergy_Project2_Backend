package com.revature.shms.controllers;

import com.revature.shms.models.Cleaning;
import com.revature.shms.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/cleaning/list")
	@ResponseBody
	public List<Cleaning> getCleaning(@RequestParam("id") int employee){
		return employeeService.employeeCleaningToDo(employeeService.getEmployeeRepository().getById(employee));
	}

}

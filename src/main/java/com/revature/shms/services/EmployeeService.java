package com.revature.shms.services;

import com.revature.shms.enums.*;
import com.revature.shms.models.*;
import com.revature.shms.repositories.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.*;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private CleaningService cleaningService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoomRepository roomRepository;

	/**
	 * Creates the Employee in the database.
	 * @param employee the given employee to match.
	 * @return Employee now saved to the database.
	 */
	public Employee createEmployee(Employee employee){
		return employeeRepository.save(employee);
	}

	/**
	 * Logs in the employee with the given username and password, then returns that Employee.
	 * @param username the username to match.
	 * @param password the password to match.
	 * @return Employee of the given username AND password.
	 * @throws AccessDeniedException if the username AND password aren't in the database together this will be thrown.
	 */
	public Employee loginEmployee(String username, String password) throws AccessDeniedException {
		try {
			Employee employee = getEmployeeByUserName(username);
			if (employee.getPassword().equals(password)) return employee;
		} catch (NotFound e) { throw new AccessDeniedException("Incorrect username/password");}
		throw new AccessDeniedException("Incorrect username/password");
	}

	/**
	 * List of All Employees ordered by Employee Type.
	 * @return List<Employee> of All employees.
	 */
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAllByOrderByEmployeeType();
	} // Tested

	/**
	 * List of All Employees with the given Employee Type.
	 * @param employeeType the employeeType to be matched.
	 * @return List<Employee> of All employees with the given employeeType.
	 */
	public List<Employee> getAllEmployeesByType(EmployeeType employeeType){
		return employeeRepository.findByEmployeeType(employeeType);
	}

	/**
	 * Gets the Employee with the matching employeeID.
	 * @param employeeID the employeeID to match.
	 * @return Employee with the given employeeID.
	 */
	public Employee getEmployeeByID(int employeeID) throws NotFound {
		return employeeRepository.findByEmployeeID(employeeID).orElseThrow(NotFound::new);
	}

	/**
	 * Gets the Employee with the matching userName.
	 * @param userName the username to match.
	 * @return Employee with the given username.
	 */
	public Employee getEmployeeByUserName(String userName) throws NotFound {
		return employeeRepository.findByUsername(userName).orElseThrow(NotFound::new);
	}

	/**
	 * Gets All Cleanings assigned to a specific employee.
	 * @param employee the employee to match.
	 * @return all Cleanings sorted that are assigned to employee.
	 */
	public List<Cleaning> employeeCleaningToDo(Employee employee){
		return cleaningService.GetAllCleaningsByEmployee(employee);
	}
}

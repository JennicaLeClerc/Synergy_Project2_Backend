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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private RoomService roomService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoomRepository roomRepository;

	/**
	 * Creates and updates the Employee in the database.
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
			Employee employee = findEmployeeByUserName(username);
			if (employee.getPassword().equals(password)) return employee;
		} catch (NotFound e) { throw new AccessDeniedException("Incorrect username/password");}
		throw new AccessDeniedException("Incorrect username/password");
	}

	// -- Updates
	/**
	 * Update password by the provided username.
	 * @param username the username that already exists on the repository.
	 * @param oldPassword the password that the user currently uses.
	 * @param newPassword the password that the user wants to switch to.
	 * Get the current username from the employee.
	 * If the username is already in the database, then we can update the password
	 */
	public boolean updatePassword(String username, String oldPassword, String newPassword) {
		Employee  employee = employeeRepository.findByUsername(username).orElse(null);
		if(employee != null) {
			if(employee.getPassword().equals(oldPassword)){
				employee.setPassword(newPassword);
				createEmployee(employee);
				return true;
			} else{
				// Username/Password invalid.
				return false;
			}
		} else{
			return false;
		}
	}

	/**
	 * Update password by the provided username.
	 * @param employeeID the employeeID that already exists on the repository.
	 * @param oldPassword the password that the employee currently uses.
	 * @param newPassword the password that the employee wants to switch to.
	 * Get the current employeeID from the employee.
	 * If the employeeID is already in the database, then we can update the password
	 */
	public boolean updatePassword(int employeeID, String oldPassword, String newPassword) {
		Employee  employee = employeeRepository.findByEmployeeID(employeeID).orElse(null);
		if(employee != null) {
			if(employee.getPassword().equals(oldPassword)){
				employee.setPassword(newPassword);
				createEmployee(employee);
				return true;
			} else{
				// Username/Password invalid.
				return false;
			}
		} else{
			return false;
		}
	}


	/**
	 * Updating the first name of the employee
	 * @param employeeID the employee to be matched witht the given employeeID
	 * @param firstName the first name to be changed to
	 * @return boolean if the em exits then change the first name of the employee
	 */
	public boolean updateFirstName(int employeeID, String firstName){
		Employee employee = employeeRepository.findByEmployeeID(employeeID).orElse(null);
		if(employee != null){
			employee.setFirstName(firstName);
			createEmployee(employee);
			return true;
		} else{
			return false;
		}
	}

	/**
	 * Updating the last name of the employee
	 * @param employeeID the employee to be matched witht the given employeeID
	 * @param lastName the last name to be changed to
	 * @return boolean if the employeeId exits then change the last name of the employee
	 */
	public boolean updateLastName(int employeeID, String lastName){
		Employee employee = employeeRepository.findByEmployeeID(employeeID).orElse(null);
		if(employee != null){
			employee.setLastName(lastName);
			createEmployee(employee);
			return true;
		} else{
			return false;
		}
	}

	// -- Finds
	/**
	 * List of All Employees ordered by Employee Type.
	 * @param pageable the page information.
	 * @return List<Employee> of All employees.
	 */
	public Page<Employee> findAllEmployees(Pageable pageable){
		return employeeRepository.findAllByOrderByEmployeeType(pageable);
	}

	/**
	 * List of All Employees with the given Employee Type.
	 * @param employeeType the employeeType to be matched.
	 * @param pageable the page information.
	 * @return List<Employee> of All employees with the given employeeType.
	 */
	public Page<Employee> findAllEmployeesByType(EmployeeType employeeType, Pageable pageable){
		return employeeRepository.findByEmployeeType(employeeType, pageable);
	}

	/**
	 * Gets the Employee with the matching employeeID.
	 * @param employeeID the employeeID to match.
	 * @return Employee with the given employeeID.
	 * @throws NotFound is thrown if the employee with the given ID does not exist.
	 */
	public Employee findEmployeeByID(int employeeID) throws NotFound {
		return employeeRepository.findByEmployeeID(employeeID).orElseThrow(NotFound::new);
	}

	/**
	 * Gets the Employee with the matching userName.
	 * @param userName the username to match.
	 * @return Employee with the given username.
	 * @throws NotFound is thrown if the employee with the given username does not exist.
	 */
	public Employee findEmployeeByUserName(String userName) throws NotFound {
		return employeeRepository.findByUsername(userName).orElseThrow(NotFound::new);
	}
}

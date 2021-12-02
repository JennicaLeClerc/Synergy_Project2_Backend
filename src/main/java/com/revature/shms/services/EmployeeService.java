package com.revature.shms.services;

import com.revature.shms.enums.Amenities;
import com.revature.shms.enums.CleaningStatus;
import com.revature.shms.enums.EmployeeType;
import com.revature.shms.exceptions.EntityNotFound;
import com.revature.shms.models.*;
import com.revature.shms.repositories.EmployeeRepository;
import com.revature.shms.repositories.RoomRepository;
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


	public Employee createEmployee(Employee employee){
		return employeeRepository.save(employee);
	}

	public Employee loginEmployee(String username, String password) throws AccessDeniedException {
		try {
			Employee employee = getEmployeeByUserName(username);
			if (employee.getPassword().equals(password)) return employee;
		} catch (EntityNotFound e) { throw new AccessDeniedException("Incorrect username/password");}
		throw new AccessDeniedException("Incorrect username/password");
	}

	public Room addRoom(Room room){
		return roomRepository.save(room);
	}

	public List<Room> addRooms(List<Room> rooms){
		return roomRepository.saveAll(rooms);
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
	} // Tested

	/**
	 * Gets the Employee with the matching employeeID.
	 * @param employeeID the employeeID to match.
	 * @return Employee with the given employeeID.
	 */
	public Employee getEmployeeByID(int employeeID) throws EntityNotFound {
		return employeeRepository.findByEmployeeID(employeeID).orElseThrow(EntityNotFound::new);
	} // Tested

	/**
	 * Gets the Employee with the matching userName.
	 * @param userName the username to match.
	 * @return Employee with the given username.
	 */
	public Employee getEmployeeByUserName(String userName) throws EntityNotFound {
		return employeeRepository.findByUsername(userName).orElseThrow(EntityNotFound::new);
	} // Tested

	/**
	 * Gets All Cleanings assigned to a specific employee.
	 * @param employee the employee to match.
	 * @return all Cleanings sorted that are assigned to employee.
	 */
	public List<Cleaning> employeeCleaningToDo(Employee employee){
		return cleaningService.GetAllCleaningsByEmployee(employee);
	} // Tested

}

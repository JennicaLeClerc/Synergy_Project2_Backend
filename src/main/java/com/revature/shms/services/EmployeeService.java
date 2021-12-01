package com.revature.shms.services;


import com.revature.shms.enums.Amenities;
import com.revature.shms.enums.EmployeeType;
import com.revature.shms.models.AmenityWrapper;
import com.revature.shms.models.Cleaning;
import com.revature.shms.models.Employee;
import com.revature.shms.models.Room;
import com.revature.shms.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

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
	public Employee getEmployeeByID(int employeeID){
		return employeeRepository.findByEmployeeID(employeeID);
	} // Tested

	/**
	 * Gets the Employee with the matching userName.
	 * @param userName the username to match.
	 * @return Employee with the given username.
	 */
	public Employee getEmployeeByUserName(String userName){
		return employeeRepository.findByUserName(userName);
	} // Tested

	/**
	 * Gets All Cleanings assigned to a specific employee.
	 * @param employee the employee to match.
	 * @return all Cleanings sorted that are assigned to employee.
	 */
	public List<Cleaning> employeeCleaningToDo(Employee employee){
		return cleaningService.GetAllCleaningsByEmployee(employee);
	} // Tested

	/**
	 *
	 * @param employee
	 * @param employeeTarget
	 * @param room the room being worked on.
	 * @param priority how quickly should the room be cleaned.
	 * @return Room scheduled to be cleaned.
	 */
	public Room scheduleCleaningRoom(Employee employee, Employee employeeTarget, Room room, int priority){
		if (employeeTarget.getEmployeeType().equals(EmployeeType.RECEPTIONIST)) return null;
		cleaningService.schedule(new Cleaning(0,room,employeeTarget,Instant.now().toEpochMilli(),priority));
		return roomService.scheduleCleaning(room);
	} // Tested
}

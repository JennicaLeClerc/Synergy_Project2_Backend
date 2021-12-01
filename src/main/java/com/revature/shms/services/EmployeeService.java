package com.revature.shms.services;

import com.revature.shms.enums.Amenities;
import com.revature.shms.enums.CleaningStatus;
import com.revature.shms.enums.EmployeeType;
import com.revature.shms.models.*;
import com.revature.shms.repositories.EmployeeRepository;
import com.revature.shms.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
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
	List<Integer> rooms;

	public Employee createEmployee(Employee employee){
		return employeeRepository.save(employee);
	}

	public Employee loginEmployee(String username, String password) throws AccessDeniedException, NotFound {
		try {
			Employee employee = getEmployeeByUserName(username);
			if (employee.getPassword().equals(password)) return employee;
		} catch (NotFound e) { throw new org.springframework.security.access.AccessDeniedException("Incorrect username/password");}
		throw new org.springframework.security.access.AccessDeniedException("Incorrect username/password");
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
	public Employee getEmployeeByID(int employeeID) throws NotFound {
		return employeeRepository.findByEmployeeID(employeeID).orElseThrow(NotFound::new);
	} // Tested

	/**
	 * Gets the Employee with the matching userName.
	 * @param userName the username to match.
	 * @return Employee with the given username.
	 */
	public Employee getEmployeeByUserName(String userName) throws NotFound {
		return employeeRepository.findByUsername(userName).orElseThrow(NotFound::new);
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
	}
	
	/**
	 *
	 * @param employee the employee doing the cleaning
	 * @param room the room to be worked on.
	 * @return Room started being cleaned.
	 */
	public Room startCleanRoom(Employee employee, Room room) throws NotFound {
		if (employee.getEmployeeType().equals(EmployeeType.RECEPTIONIST)) return null;
		cleaningService.remove(cleaningService.getByRoom(room));
		return roomService.startCleaning(room);
	}
	
	/**
	 *
	 * @param employee the employee doing the cleaning
	 * @param room the room to be worked on.
	 * @return Room now finished being cleaned.
	 */
	public Room finishCleaningRoom(Employee employee, Room room){
		if (employee.getEmployeeType().equals(EmployeeType.RECEPTIONIST)) return null;
		return roomService.finishCleaning(room);
	}

	/*
	 This method allows the employees to see all the rooms status
	 */
	public List<Room> findAllByStatus(CleaningStatus status){
		return findAllByStatus(status);

	}
}

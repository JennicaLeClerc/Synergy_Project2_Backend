package com.revature.shms.servicetests;

import com.revature.shms.enums.CleaningStatus;
import com.revature.shms.enums.EmployeeType;
import com.revature.shms.models.Cleaning;
import com.revature.shms.models.Employee;
import com.revature.shms.models.Room;
import com.revature.shms.repositories.EmployeeRepository;
import com.revature.shms.services.CleaningService;
import com.revature.shms.services.EmployeeService;
import com.revature.shms.services.RoomService;
import com.revature.shms.services.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

	@Mock EmployeeRepository employeeRepository;
	@Mock CleaningService cleaningService;
	@Mock UserService userService;
	@Mock RoomService roomService;
	@InjectMocks EmployeeService employeeService;

	@Test
	public void getAllEmployeesTest(){
		Employee employee = new Employee();
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee);
		when(employeeRepository.findAllByOrderByEmployeeType()).thenReturn(employeeList);
		Assertions.assertEquals(employeeList, employeeService.getAllEmployees());
	}

	@Test
	public void getAllEmployeesByTypeTest(){
		EmployeeType employeeType = null;
		Employee employee = new Employee();
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee);
		when(employeeRepository.findByEmployeeType(any())).thenReturn(employeeList);
		Assertions.assertEquals(employeeList, employeeService.getAllEmployeesByType(employeeType));
	}

	@Test
	public void getEmployeeByIDTest(){
		Employee employee = new Employee();
		when(employeeRepository.findByEmployeeID(anyInt())).thenReturn(employee);
		Assertions.assertEquals(employee, employeeService.getEmployeeByID(0));
	}

	@Test
	public void getEmployeeByUserNameTest(){
		String username = null;
		Employee employee = new Employee();
		when(employeeRepository.findByUserName(any())).thenReturn(employee);
		Assertions.assertEquals(employee, employeeService.getEmployeeByUserName(username));
	}

	@Test
	public void employeeCleaningToDoTest(){
		Employee employee = new Employee();
		Cleaning cleaning = new Cleaning();
		List<Cleaning> cleaningList = new ArrayList<>();
		cleaningList.add(cleaning);
		when(cleaningService.GetAllCleaningsByEmployee(any())).thenReturn(cleaningList);
		Assertions.assertEquals(cleaningList, employeeService.employeeCleaningToDo(employee));
	}

	@Test
	public void scheduleCleaningRoomTest(){
		Employee employee = new Employee();
		Cleaning cleaning = new Cleaning();
		Room room = new Room();
		employee.setEmployeeType(EmployeeType.RECEPTIONIST);
		Assertions.assertNull( employeeService.scheduleCleaningRoom(null,employee,null,0));
		when(cleaningService.schedule(any())).thenReturn(cleaning);
		when(roomService.scheduleCleaning(any())).thenReturn(room);
		employee.setEmployeeType(EmployeeType.MAINTENANCE);
		room.setStatus(CleaningStatus.NOT_SCHEDULED);
		Assertions.assertEquals(room,employeeService.scheduleCleaningRoom(null,employee,room,0));
	}

	@Test
	public void settersGettersTest(){
		EmployeeService employeeService = new EmployeeService();
		CleaningService cleaningService = new CleaningService();
		RoomService roomService = new RoomService();
		UserService userService = new UserService();

		employeeService.setCleaningService(cleaningService);
		employeeService.setRoomService(roomService);
		employeeService.setUserService(userService);

		Assertions.assertEquals(cleaningService,employeeService.getCleaningService());
		Assertions.assertEquals(roomService,employeeService.getRoomService());
		Assertions.assertEquals(userService,employeeService.getUserService());
	}
}

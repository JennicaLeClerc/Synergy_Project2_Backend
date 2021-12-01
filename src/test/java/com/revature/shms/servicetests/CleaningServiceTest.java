package com.revature.shms.servicetests;
import com.revature.shms.enums.CleaningStatus;
import com.revature.shms.enums.EmployeeType;
import com.revature.shms.models.Cleaning;
import com.revature.shms.models.Employee;
import com.revature.shms.models.Room;
import com.revature.shms.repositories.CleaningRepository;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CleaningServiceTest {
	@Mock RoomService roomService;
	@Mock CleaningRepository cleaningRepository;
	@InjectMocks CleaningService service;

	@Test
	public void scheduleCleaningRoomTest(){
		Employee employee = new Employee();
		employee.setEmployeeType(EmployeeType.RECEPTIONIST);
		Assertions.assertNull( service.scheduleCleaningRoom(null,employee,null,0));
		when(service.schedule(any())).thenReturn(new Cleaning());
		Room room = new Room();
		when(roomService.scheduleCleaning(any())).thenReturn(room);
		employee.setEmployeeType(EmployeeType.MAINTENANCE);
		room.setStatus(CleaningStatus.NOT_SCHEDULED);
		Assertions.assertEquals(room,service.scheduleCleaningRoom(null,employee,room,0));
	}
	@Test
	public void startCleanRoomTest(){
		Employee employee = new Employee();
		employee.setEmployeeType(EmployeeType.RECEPTIONIST);
		Assertions.assertNull( service.startCleanRoom(employee,null));
		Room room = new Room();
		when(roomService.startCleaning(any())).thenReturn(room);
		employee.setEmployeeType(EmployeeType.MAINTENANCE);
		room.setStatus(CleaningStatus.NOT_SCHEDULED);
		Assertions.assertEquals(room,service.startCleanRoom(employee,room));
	}
	@Test
	public void GetAllCleaningsTest(){
		List<Cleaning> cleanings = new ArrayList<>();
		cleanings.add(new Cleaning());
		cleanings.add(new Cleaning());
		cleanings.add(new Cleaning());
		cleanings.add(new Cleaning());
		when(cleaningRepository.findAllByOrderByPriorityDescDateAddedAsc()).thenReturn(cleanings);
		Assertions.assertEquals(service.GetAllCleanings(),cleanings);
	}

	@Test
	public void gettersSetters(){
		CleaningRepository cleaningRepository = null;
		RoomService roomService = new RoomService();
		CleaningService cleaningService = new CleaningService(cleaningRepository,roomService);
		Assertions.assertNull(cleaningService.getCleaningRepository());
		Assertions.assertEquals(cleaningService.getRoomService(),roomService);
		cleaningService.setRoomService(null);
		cleaningService.setCleaningRepository(null);
		Assertions.assertNull(cleaningService.getCleaningRepository());
		Assertions.assertNull(cleaningService.getRoomService());

	}

	@Test
	public void finishCleaningRoomTest(){
		Employee employee = new Employee();
		employee.setEmployeeType(EmployeeType.RECEPTIONIST);
		Assertions.assertNull( service.finishCleaningRoom(employee,null));
		Room room = new Room();
		when(roomService.finishCleaning(any())).thenReturn(room);
		employee.setEmployeeType(EmployeeType.MAINTENANCE);
		room.setStatus(CleaningStatus.NOT_SCHEDULED);
		Assertions.assertEquals(room,service.finishCleaningRoom(employee,room));
	}
	@Test
	public void employeeCleaningToDoTest(){
		when(service.GetAllCleaningsByEmployee(any())).thenReturn(null);
		Assertions.assertNull(service.employeeCleaningToDo(new Employee()));
	}

}

package com.revature.shms.servicetests;

import com.revature.shms.enums.CleaningStatus;
import com.revature.shms.enums.EmployeeType;
import com.revature.shms.models.Cleaning;
import com.revature.shms.models.Employee;
import com.revature.shms.models.Room;
import com.revature.shms.repositories.CleaningRepository;
import com.revature.shms.services.CleaningService;
import com.revature.shms.services.RoomService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CleaningServiceTest {
	@Mock RoomService roomService;
	@Mock CleaningRepository cleaningRepository;
	@InjectMocks CleaningService cleaningService;

	@Test
	public void employeeCleaningToDoTest(){
		when(cleaningService.findAllCleaningsByEmployee(any(),any())).thenReturn(null);
		assertNull(cleaningService.employeeCleaningToDo(new Employee(), null));
	}

	@Test
	public void scheduleCleaningRoomTest(){
		Employee employee = new Employee();
		employee.setEmployeeType(EmployeeType.RECEPTIONIST);
		assertNull( cleaningService.scheduleCleaningRoom(null, employee, null, 0));
		when(cleaningService.schedule(any())).thenReturn(new Cleaning());
		Room room = new Room();
		when(roomService.scheduleCleaning(any())).thenReturn(room);
		employee.setEmployeeType(EmployeeType.MAINTENANCE);
		room.setStatus(CleaningStatus.NOT_SCHEDULED);
		assertEquals(room, cleaningService.scheduleCleaningRoom(null, employee, room, 0));
	}

	@Test
	public void startCleanRoomTest() throws NotFound {
		Employee employee = new Employee();
		employee.setEmployeeType(EmployeeType.RECEPTIONIST);
		assertNull( cleaningService.startCleanRoom(employee, null));
		Room room = new Room();
		when(roomService.startCleaning(any())).thenReturn(room);
		employee.setEmployeeType(EmployeeType.MAINTENANCE);
		room.setStatus(CleaningStatus.NOT_SCHEDULED);
		when(cleaningRepository.findByRoom(any())).thenReturn(java.util.Optional.of(new Cleaning()));
		assertEquals(room, cleaningService.startCleanRoom(employee, room));
	}

	@Test
	public void finishCleaningRoomTest(){
		Employee employee = new Employee();
		employee.setEmployeeType(EmployeeType.RECEPTIONIST);
		assertNull( cleaningService.finishCleaningRoom(employee, null));
		Room room = new Room();
		when(roomService.finishCleaning(any())).thenReturn(room);
		employee.setEmployeeType(EmployeeType.MAINTENANCE);
		room.setStatus(CleaningStatus.NOT_SCHEDULED);
		assertEquals(room, cleaningService.finishCleaningRoom(employee, room));
	}

	@Test
	public void getAllCleaningsTest(){
		List<Cleaning> cleanings = new ArrayList<>();
		cleanings.add(new Cleaning());
		cleanings.add(new Cleaning());
		cleanings.add(new Cleaning());
		cleanings.add(new Cleaning());
		Page<Cleaning> cleaningPage = new PageImpl<>(cleanings);
		when(cleaningRepository.findAllByOrderByPriorityDescDateAddedAsc(any())).thenReturn(cleaningPage);
		assertEquals(cleanings, cleaningService.findAllCleanings(any()).getContent());
	}

	@Test
	public void getAllCleaningsByEmployeeTest(){
		List<Cleaning> cleaningList = new ArrayList<>();
		cleaningList.add(new Cleaning());
		Page<Cleaning> cleaningPage = new PageImpl<>(cleaningList);
		when(cleaningRepository.findAllByEmployeeOrderByPriorityDescDateAddedAsc(any(),any())).thenReturn(cleaningPage);
		assertEquals(cleaningList, cleaningService.findAllCleaningsByEmployee(new Employee(),null).getContent());
	}

	@Test
	public void getByRoomTest() throws NotFound {
		Cleaning cleaning = new Cleaning();
		when(cleaningRepository.findByRoom(any())).thenReturn(java.util.Optional.of(cleaning));
		assertEquals(cleaning, cleaningService.findByRoom(new Room()));
	}

	@Test
	public void scheduleTest(){
		Cleaning cleaning = new Cleaning();
		when(cleaningRepository.save(any())).thenReturn(cleaning);
		assertEquals(cleaning, cleaningService.schedule(cleaning));
	}

	@Test
	public void removeTest(){
		Cleaning cleaning = new Cleaning();
		cleaningService.remove(cleaning);
		verify(cleaningRepository, times(1)).delete(any());
	}

	@Test
	public void gettersSetters(){
		CleaningRepository cleaningRepository = null;
		RoomService roomService = new RoomService();

		CleaningService cleaningService = new CleaningService();
		cleaningService.setCleaningRepository(cleaningRepository);
		cleaningService.setRoomService(roomService);

		assertNull(cleaningService.getCleaningRepository());
		assertEquals(cleaningService.getRoomService(),roomService);

		cleaningService.setRoomService(null);
		cleaningService.setCleaningRepository(null);

		assertNull(cleaningService.getCleaningRepository());
		assertNull(cleaningService.getRoomService());
	}
}

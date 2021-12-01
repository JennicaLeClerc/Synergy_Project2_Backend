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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

	@Mock EmployeeRepository employeeRepository;
	@Mock CleaningService cleaningService;
	@Mock UserService userService;
	@Mock RoomService roomService;
	@InjectMocks EmployeeService employeeService;





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

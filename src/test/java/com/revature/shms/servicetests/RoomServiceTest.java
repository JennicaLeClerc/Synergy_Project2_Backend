package com.revature.shms.servicetests;

import com.revature.shms.enums.CleaningStatus;
import com.revature.shms.models.Room;
import com.revature.shms.services.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
	@Test
	public void setRoomStatusTest(){
		RoomService roomService = new RoomService();
		Room room = new Room();
		room.setStatus(CleaningStatus.NOT_SCHEDULED);
		for (CleaningStatus cleaningStatus:CleaningStatus.values()) {
			Assertions.assertEquals(cleaningStatus,roomService.setRoomStatus(room,cleaningStatus).getStatus());
		}
	}

	@Test
	public void scheduleCleaningTest(){
		RoomService roomService = new RoomService();
		Room room = new Room();
		Assertions.assertEquals(CleaningStatus.SCHEDULED,roomService.scheduleCleaning(room).getStatus());
	}

	@Test
	public void startCleaningTest(){
		RoomService roomService = new RoomService();
		Room room = new Room();
		Assertions.assertEquals(CleaningStatus.IN_PROGRESS,roomService.startCleaning(room).getStatus());
	}

	@Test
	public void finishCleaning(){
		RoomService roomService = new RoomService();
		Room room = new Room();
		Assertions.assertEquals(CleaningStatus.CLEAN,roomService.finishCleaning(room).getStatus());
	}
}

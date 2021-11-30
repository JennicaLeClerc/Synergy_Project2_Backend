package com.revature.shms.services;


import com.revature.shms.enums.CleaningStatus;
import com.revature.shms.models.Room;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
	/*TODO
		isAvailable
		isClean
		needWork
	 */
	public Room setRoomStatus(Room room, CleaningStatus cleaningStatus){
		room.setStatus(cleaningStatus);
		return room;
	}
	public Room scheduleCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.SCHEDULED);
	}
	public Room startCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.IN_PROGRESS);
	}
	public Room finishCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.CLEAN);
	}
}

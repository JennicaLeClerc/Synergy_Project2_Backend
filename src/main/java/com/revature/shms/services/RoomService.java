package com.revature.shms.services;


import com.revature.shms.enums.CleaningStatus;
import com.revature.shms.models.Room;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
	/*TODO
		isAvailable
		isClean // Done???
		needWork
	 */

	// --- isClean portion ---
	/**
	 *
	 * @param room the room to be worked on.
	 * @param cleaningStatus the status of the cleaning.
	 * @return Room with the current cleaning status.
	 */
	public Room setRoomStatus(Room room, CleaningStatus cleaningStatus){
		room.setStatus(cleaningStatus);
		return room;
	}

	/**
	 *
	 * @param room the room to be worked on.
	 * @return Room with a scheduled cleaning status.
	 */
	public Room scheduleCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.SCHEDULED);
	}

	/**
	 *
	 * @param room the room to be worked on.
	 * @return Room with an in progress cleaning status.
	 */
	public Room startCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.IN_PROGRESS);
	}

	/**
	 *
	 * @param room the room to be worked on.
	 * @return Room with a clean cleaning status.
	 */
	public Room finishCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.CLEAN);
	}
}

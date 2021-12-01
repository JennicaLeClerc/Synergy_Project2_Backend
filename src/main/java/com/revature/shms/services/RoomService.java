package com.revature.shms.services;


import com.revature.shms.enums.Amenities;
import com.revature.shms.enums.CleaningStatus;
import com.revature.shms.models.Room;
import com.revature.shms.repositories.EmployeeRepository;
import com.revature.shms.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
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
	} // Tested

	/**
	 *
	 * @param room the room to be worked on.
	 * @return Room with a scheduled cleaning status.
	 */
	public Room scheduleCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.SCHEDULED);
	} // Tested

	/**
	 *
	 * @param room the room to be worked on.
	 * @return Room with an in progress cleaning status.
	 */
	public Room startCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.IN_PROGRESS);
	} // Tested

	/**
	 *
	 * @param room the room to be worked on.
	 * @return Room with a clean cleaning status.
	 */
	public Room finishCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.CLEAN);
	} // Tested

	public List<Room> getAllRooms(){
		return roomRepository.findAllByOrderByRoomNumberDesc();
	}

	public List<Room> getAllByStatus(CleaningStatus status){
		return roomRepository.findAllByStatus(status);
	}

	public List<Room> getAllByIsOccupied(boolean isOccupied){
		return roomRepository.findAllByIsOccupied(isOccupied);
	}

	public List<Room> getAllByNeedsService(boolean needsService){
		return roomRepository.findAllByNeedsService(needsService);
	}

	public List<Room> getAllByAmenity(Amenities amenity){
		return roomRepository.findAllByAmenitiesList_Amenity(amenity);
	}

	public Room getByRoomNumber(int roomNumber){
		return roomRepository.findByRoomNumber(roomNumber);
	}
}

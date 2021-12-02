package com.revature.shms.services;


import com.revature.shms.enums.*;
import com.revature.shms.models.Room;
import com.revature.shms.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	/**
	 * Returns a boolean if the room is clean with no work being done and is not occupied.
	 * @param room the room to be worked on.
	 * @return boolean of if the room is avaliable or not.
	 */
	public boolean isAvailable(Room room){
		return room.getStatus().equals(CleaningStatus.CLEAN) && room.getWorkStatus().equals(WorkStatus.NO_ISSUES)
				&& !room.isOccupied();
	}

	// --- isOccupied ---
	/**
	 * Sets the room Occupation status of the given room to the given staus.
	 * @param room the room to be worked on.
	 * @param isOccupied is the room occupied or not.
	 * @return the room with the new occupation status.
	 */
	public Room setOccupationStatus(Room room, boolean isOccupied){
		room.setOccupied(isOccupied);
		return room;
	}

	/**
	 * Set the room Occupation status to false, aka. not Occupied.
	 * @param room the room to be worked on.
	 * @return the room with the occupation status set to false.
	 */
	public Room notOccupied(Room room){
		return setOccupationStatus(room, false);
	}

	/**
	 * Set the room Occupation status to true, aka. Occupied.
	 * @param room the room to be worked on.
	 * @return the room with the occupation status set to true.
	 */
	public Room Occupied(Room room){
		return setOccupationStatus(room, true);
	}

	// --- isClean portion ---
	/**
	 * Sets the room cleaning status of the given room to the given status.
	 * @param room the room to be worked on.
	 * @param cleaningStatus the status of the cleaning.
	 * @return Room with the current cleaning status.
	 */
	public Room setRoomStatus(Room room, CleaningStatus cleaningStatus){
		room.setStatus(cleaningStatus);
		return room;
	}

	/**
	 * Sets the room cleaning status to being Scheduled.
	 * @param room the room to be worked on.
	 * @return Room with a scheduled cleaning status.
	 */
	public Room scheduleCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.SCHEDULED);
	}

	/**
	 * Sets the room cleaning status to Not Scheduled.
	 * @param room the room to be worked on.
	 * @return Room with no scheduled cleaning.
	 */
	public Room notScheduleCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.NOT_SCHEDULED);
	}

	/**
	 * Sets the room cleaning status to In Progress.
	 * @param room the room to be worked on.
	 * @return Room with an in progress cleaning status.
	 */
	public Room startCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.IN_PROGRESS);
	}

	/**
	 * Sets the room cleaning status to Clean.
	 * @param room the room to be worked on.
	 * @return Room with a clean cleaning Status.
	 */
	public Room finishCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.CLEAN);
	}

	// --- WorkStatus ---
	/**
	 * Set the room with the given work status.
	 * @param room the room to be worked on.
	 * @param workStatus the status of the work on the room.
	 * @return the room with the selected work status.
	 */
	public Room setWorkStatus(Room room, WorkStatus workStatus){
		room.setWorkStatus(workStatus);
		return room;
	}

	public Room startWorking(Room room){
		return setWorkStatus(room, WorkStatus.IN_PROGRESS);
	}

	public Room scheduleWorking(Room room){
		return setWorkStatus(room, WorkStatus.SCHEDULED);}

	public Room notScheduleWorking(Room room){
		return setWorkStatus(room, WorkStatus.NOT_SCHEDULED);
	}

	public Room finishWorking(Room room){
		return setWorkStatus(room, WorkStatus.NO_ISSUES);
	}

	// --- Finds ---
	/**
	 * @return List<Room> Ordered by Room Number in descending order.
	 */
	public List<Room> getAllRooms(){
		return roomRepository.findAllByOrderByRoomNumberDesc();
	}

	/**
	 * Gets all Rooms with the given Cleaning Status.
	 * @param status the Cleaning Status to be matched.
	 * @return List<Room> of all rooms with the given Cleaning Status.
	 */
	public List<Room> getAllByStatus(CleaningStatus status){
		return roomRepository.findAllByStatus(status);
	}

	/**
	 * Gets all Rooms with the given Occupation status.
	 * @param isOccupied is the room Occupied or not
	 * @return List<Room> of all rooms that are the given occupation status.
	 */
	public List<Room> getAllByIsOccupied(boolean isOccupied){
		return roomRepository.findAllByIsOccupied(isOccupied);
	}

	/**
	 * Gets all Rooms with the given Needs Service status.
	 * @param workStatus the Work Status of the room.
	 * @return List<Room> of all rooms that are the given Needs Service status.
	 */
	public List<Room> getAllByNeedsService(WorkStatus workStatus){
		return roomRepository.findAllByWorkStatus(workStatus);
	}

	/**
	 * Gets all Rooms with the given Amenity.
	 * @param amenity the Amenity to be matched.
	 * @return List<Room> of all rooms that have the given Amenity.
	 */
	public List<Room> getAllByAmenity(Amenities amenity){
		return roomRepository.findAllByAmenitiesList_Amenity(amenity);
	}

	/**
	 * A Room with the given Room Number.
	 * @param roomNumber the Room Number to be matched.
	 * @return Room with the given Room Number.
	 * @throws NotFound
	 */
	public Room getByRoomNumber(int roomNumber) throws NotFound {
		return roomRepository.findByRoomNumber(roomNumber).orElseThrow(NotFound::new);
	}
}

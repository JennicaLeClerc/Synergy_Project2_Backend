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
	 * Saves the given room to the database.
	 * @param room the room to be saved.
	 * @return Room now saved to the database.
	 */
	public Room addRoom(Room room){
		return roomRepository.save(room);
	}

	/**
	 * Saves the given list of rooms to the database.
	 * @param rooms a list of rooms to be saved.
	 * @return List<Room> now saved to the database.
	 */
	public List<Room> addRooms(List<Room> rooms){
		return roomRepository.saveAll(rooms);
	}

	// --- isAvailable ---
	/**
	 * Returns a boolean if the room is clean with no work being done and is not occupied.
	 * @param room the room to be worked on.
	 * @return boolean of if the room is avaliable or not.
	 */
	public boolean isAvailable(Room room){
		return room.getStatus().equals(CleaningStatus.CLEAN) && room.getWorkStatus().equals(WorkStatus.NO_ISSUES)
				&& !room.isOccupied();
	}

	public List<Room> findAllAvailable(){
		return roomRepository.findAllByStatusAndIsOccupiedAndWorkStatusOrderByRoomNumberDesc(
				CleaningStatus.CLEAN, false, WorkStatus.NO_ISSUES);
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
	 * Gets all Rooms with the given Occupation status.
	 * @param isOccupied is the room Occupied or not
	 * @return List<Room> of all rooms that are the given occupation status.
	 */
	public List<Room> getAllByIsOccupied(boolean isOccupied){
		return roomRepository.findAllByIsOccupied(isOccupied);
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
	 * Gets all Rooms with the given Cleaning Status.
	 * @param status the Cleaning Status to be matched.
	 * @return List<Room> of all rooms with the given Cleaning Status.
	 */
	public List<Room> getAllByStatus(CleaningStatus status){
		return roomRepository.findAllByStatus(status);
	}

	/**
	 * Gets all Rooms without the given Cleaning status.
	 * @param status the Cleaning Status to NOT be matched.
	 * @return List<Room> of all rooms without the given Cleaning Status.
	 */
	public List<Room> findAllByNotStatus(CleaningStatus status){
		return roomRepository.findAllByStatusNot(status);
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

	/**
	 * Gets all Rooms with the given Needs Service status.
	 * @param workStatus the Work Status to be matched.
	 * @return List<Room> of all rooms that are the given Needs Service status.
	 */
	public List<Room> getAllByWorkStatus(WorkStatus workStatus){
		return roomRepository.findAllByWorkStatus(workStatus);
	}

	/**
	 * Gets all Rooms without the given Work Status.
	 * @param workStatus the Work Status to NOT be matched.
	 * @return List<Room> of all rooms without the given Work Status.
	 */
	public List<Room> findAllByNotWorkStatus(WorkStatus workStatus){
		return roomRepository.findAllByWorkStatusNot(workStatus);
	}

	/**
	 * Sets the Work Status of the given room to In Progress.
	 * @param room the room to be worked on.
	 * @return the room with an In Progress work status.
	 */
	public Room startWorking(Room room){
		return setWorkStatus(room, WorkStatus.IN_PROGRESS);
	}

	/**
	 * Sets the Work Status of the given room to Scheduled.
	 * @param room the room to be worked on.
	 * @return the room with a Scheduled work status.
	 */
	public Room scheduleWorking(Room room){
		return setWorkStatus(room, WorkStatus.SCHEDULED);
	}

	/**
	 * Sets the Work Status of the given room to Not Scheduled.
	 * @param room the room to be worked on.
	 * @return the room with a Not Scheduled work status.
	 */
	public Room notScheduleWorking(Room room){
		return setWorkStatus(room, WorkStatus.NOT_SCHEDULED);
	}

	/**
	 * Sets the Work Status of the given room to No Issues.
	 * @param room the room to be worked on.
	 * @return the room with a No Issues work status.
	 */
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
	 * @throws NotFound if the room number does not exist.
	 */
	public Room getByRoomNumber(int roomNumber) throws NotFound {
		return roomRepository.findByRoomNumber(roomNumber).orElseThrow(NotFound::new);
	}
}

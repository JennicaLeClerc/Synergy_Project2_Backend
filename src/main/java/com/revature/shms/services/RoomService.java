package com.revature.shms.services;


import com.revature.shms.enums.Amenities;
import com.revature.shms.enums.CleaningStatus;
import com.revature.shms.models.Room;
import com.revature.shms.repositories.EmployeeRepository;
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
	/*TODO
		isAvailable
		isClean // Done???
		needWork
	 */
	// --- isClean portion ---
	/**
	 * Sets the room cleaning status to the given room to the given status.
	 * @param room the room to be worked on.
	 * @param cleaningStatus the status of the cleaning.
	 * @return Room with the current cleaning status.
	 */
	public Room setRoomStatus(Room room, CleaningStatus cleaningStatus){
		room.setStatus(cleaningStatus);
		return room;
	} // Tested

	/**
	 * Sets the room cleaning status to being Scheduled.
	 * @param room the room to be worked on.
	 * @return Room with a scheduled cleaning status.
	 */
	public Room scheduleCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.SCHEDULED);
	} // Tested

	/**
	 * Sets the room cleaning status to In Progress.
	 * @param room the room to be worked on.
	 * @return Room with an in progress cleaning status.
	 */
	public Room startCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.IN_PROGRESS);
	} // Tested

	/**
	 * Sets the room cleaning status to Clean.
	 * @param room the room to be worked on.
	 * @return Room with a clean cleaning Status.
	 */
	public Room finishCleaning(Room room){
		return setRoomStatus(room, CleaningStatus.CLEAN);
	} // Tested

	/**
	 * @return List<Room> Ordered by Room Number in descending order.
	 */
	public List<Room> getAllRooms(){
		return roomRepository.findAllByOrderByRoomNumberDesc();
	} //Tested

	/**
	 * Gets all Rooms with the given Cleaning Status.
	 * @param status the Cleaning Status to be matched.
	 * @return List<Room> of all rooms with the given Cleaning Status.
	 */
	public List<Room> getAllByStatus(CleaningStatus status){
		return roomRepository.findAllByStatus(status);
	} // Tested

	/**
	 * Gets all Rooms with the given Occupation status.
	 * @param isOccupied is the room Occupied or not
	 * @return List<Room> of all rooms that are the given occupation status.
	 */
	public List<Room> getAllByIsOccupied(boolean isOccupied){
		return roomRepository.findAllByIsOccupied(isOccupied);
	} // Tested

	/**
	 * Gets all Rooms with the given Needs Service status.
	 * @param needsService is the room in need of service or not
	 * @return List<Room> of all rooms that are the given Needs Service status.
	 */
	public List<Room> getAllByNeedsService(boolean needsService){
		return roomRepository.findAllByNeedsService(needsService);
	} // Tested

	/**
	 * Gets all Rooms with the given Amenity.
	 * @param amenity the Amenity to be matched.
	 * @return List<Room> of all rooms that have the given Amenity.
	 */
	public List<Room> getAllByAmenity(Amenities amenity){
		return roomRepository.findAllByAmenitiesList_Amenity(amenity);
	} // Tested

	/**
	 * A Room with the given Room Number.
	 * @param roomNumber the Room Number to be matched.
	 * @return Room with the given Room Number.
	 * @throws NotFound
	 */
	public Room getByRoomNumber(int roomNumber) throws NotFound {
		return roomRepository.findByRoomNumber(roomNumber).orElseThrow(NotFound::new);
	} // Tested


	/**
	 * Find all room status.
	 * @param status the status of all the room.
	 */
	public List<Room> findAllByStatus(CleaningStatus status){
		return findAllByStatus(status);
	}
}




package com.revature.shms.services;


import com.revature.shms.enums.EmployeeType;
import com.revature.shms.exceptions.EntityNotFound;
import com.revature.shms.models.Cleaning;
import com.revature.shms.models.Employee;
import com.revature.shms.models.Room;
import com.revature.shms.repositories.CleaningRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CleaningService {
	@Autowired
	private CleaningRepository cleaningRepository;
	@Autowired
	private RoomService roomService;

	/**
	 * Gets All Cleanings assigned to a specific employee.
	 * @param employee the employee to match.
	 * @return all Cleanings sorted that are assigned to employee.
	 */
	public List<Cleaning> employeeCleaningToDo(Employee employee){
		return GetAllCleaningsByEmployee(employee);
	}

	/**
	 * Ryan wanted to still work on this
	 * @param employee ?
	 * @param employeeTarget ?
	 * @param room the room being worked on.
	 * @param priority how quickly should the room be cleaned.
	 * @return Room scheduled to be cleaned.
	 */
	public Room scheduleCleaningRoom(Employee employee, Employee employeeTarget, Room room, int priority){
		if (employeeTarget.getEmployeeType().equals(EmployeeType.RECEPTIONIST)) return null;
		schedule(new Cleaning(0,room,employeeTarget, Instant.now().toEpochMilli(),priority));
		return roomService.scheduleCleaning(room);
	}

	/**
	 * If you aren't a Receptionist, then you delete the previous Cleaning ID for that room number and start another
	 * with the Cleaning status of In Progress.
	 * @param employee the employee doing the cleaning
	 * @param room the room to be worked on.
	 * @return Room started being cleaned.
	 */
	public Room startCleanRoom(Employee employee, Room room) throws EntityNotFound {
		if (employee.getEmployeeType().equals(EmployeeType.RECEPTIONIST)) return null;
		remove(getByRoom(room));
		return roomService.startCleaning(room);
	}

	/**
	 * If you aren't a Receptionist, then you set the Cleaning status of the given room to Clean.
	 * @param employee the employee doing the cleaning
	 * @param room the room to be worked on.
	 * @return Room now finished being cleaned.
	 */
	public Room finishCleaningRoom(Employee employee, Room room){
		if (employee.getEmployeeType().equals(EmployeeType.RECEPTIONIST)) return null;
		return roomService.finishCleaning(room);
	}

	/**
	 * Gets All Cleanings by Priority then DateAdded.
	 * @return List<Cleaning> Sorted by Priority and DateAdded.
	 */
	public List<Cleaning> GetAllCleanings(){
		return cleaningRepository.findAllByOrderByPriorityDescDateAddedAsc();
	} // Tested

	/**
	 * Gets All Cleanings assigned to a specific employee.
	 * @param employee the employee to match.
	 * @return all Cleanings sorted that are assigned to employee.
	 */
	public List<Cleaning> GetAllCleaningsByEmployee(Employee employee){
		return cleaningRepository.findAllByEmployeeOrderByPriorityDescDateAddedAsc(employee);
	} // Tested

	/**
	 * Gets Cleaning assigned to a specific room.
	 * @param room the room to match.
	 * @return Cleaning corresponding to the room.
	 */
	public Cleaning getByRoom(Room room) throws EntityNotFound {
		return cleaningRepository.findByRoom(room).orElseThrow(EntityNotFound::new);
	} // Tested

	/**
	 * Saves the cleaning information to the database and returns the original object
	 * @param cleaning the cleaning information to match.
	 * @return the cleaning information with it saved to the database.
	 */
	public Cleaning schedule(Cleaning cleaning){
		return cleaningRepository.save(cleaning);
	} // Tested

	/**
	 * Deletes the cleaning information from the database
	 * @param cleaning the cleaning information to match
	 */
	public void remove(Cleaning cleaning){
		cleaningRepository.delete(cleaning);
	}// Tested
}

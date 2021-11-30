package com.revature.shms.services;


import com.revature.shms.models.Cleaning;
import com.revature.shms.models.Employee;
import com.revature.shms.models.Room;
import com.revature.shms.repositories.CleaningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CleaningService {
	@Autowired
	private CleaningRepository cleaningRepository;

	/**
	 * Gets All Cleanings by Priority then DateAdded.
	 * @return List<Cleaning> Sorted by Priority and DateAdded.
	 */
	public List<Cleaning> GetAllCleanings(){
		return cleaningRepository.findAllByOrderByPriorityDescDateAddedAsc();
	}

	/**
	 * Gets All Cleanings assigned to a specific employee.
	 * @param employee the employee to match.
	 * @return all Cleanings sorted that are assigned to employee.
	 */
	public List<Cleaning> GetAllCleaningsByEmployee(Employee employee){
		return cleaningRepository.findByEmployeeOrderByPriorityDescDateAddedAsc(employee);
	}

	/**
	 * Saves the cleaning information to the database and returns the orginal object
	 * @param cleaning the cleaning information to match.
	 * @return the cleaning information with it saved to the database.
	 */
	public Cleaning schedule(Cleaning cleaning){
		return cleaningRepository.save(cleaning);
	}

	/**
	 * Deletes the cleaning information from the database
	 * @param cleaning the cleaning information to match
	 */
	public void remove(Cleaning cleaning){
		cleaningRepository.delete(cleaning);
	}

	/**
	 * Gets Cleaning assigned to a specific room.
	 * @param room the room to match.
	 * @return Cleaning corresponding to the room.
	 */
	public Cleaning getByRoom(Room room){
		return cleaningRepository.findByRoom(room);
	}
}

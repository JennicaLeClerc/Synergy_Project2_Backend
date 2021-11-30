package com.revature.shms.repositories;


import com.revature.shms.models.Cleaning;
import com.revature.shms.models.Employee;
import com.revature.shms.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CleaningRepository  extends JpaRepository<Cleaning,Integer> {

	/**
	 * Gets All Cleanings by Priority then DateAdded
	 * @return List<Cleaning> Sorted by Priority and DateAdded
	 */
	List<Cleaning> findAllOrderByPriorityDescDateAddedAsc();

	/**
	 * Gets All Cleanings assigned to a specific employee
	 * @param employee the employee to match
	 * @return all Cleanings sorted that are assigned to employee
	 */
	List<Cleaning> findByEmployeeOrderByPriorityDescDateAddedAsc(Employee employee);
	Cleaning findByRoom(Room room);
}

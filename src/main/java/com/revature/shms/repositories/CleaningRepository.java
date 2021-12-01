package com.revature.shms.repositories;

import com.revature.shms.models.Cleaning;
import com.revature.shms.models.Employee;
import com.revature.shms.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CleaningRepository  extends JpaRepository<Cleaning,Integer> {

	List<Cleaning> findAllByOrderByPriorityDescDateAddedAsc();

	List<Cleaning> findAllByEmployeeOrderByPriorityDescDateAddedAsc(Employee employee);

	Cleaning findByRoom(Room room);
}

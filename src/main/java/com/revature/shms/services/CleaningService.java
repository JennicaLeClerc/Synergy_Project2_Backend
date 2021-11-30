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

	public List<Cleaning> GetAllCleanings(){
		return cleaningRepository.findAllByOrderByPriorityDescDateAddedAsc();
	}

	public List<Cleaning> GetAllCleaningsByEmployee(Employee employee){
		return cleaningRepository.findByEmployeeOrderByPriorityDescDateAddedAsc(employee);
	}
	public Cleaning schedule(Cleaning cleaning){
		return cleaningRepository.save(cleaning);
	}
	public void remove(Cleaning cleaning){
		cleaningRepository.delete(cleaning);
	}
	public Cleaning getByRoom(Room room){
		return cleaningRepository.findByRoom(room);
	}


}

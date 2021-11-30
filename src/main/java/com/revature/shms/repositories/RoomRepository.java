package com.revature.shms.repositories;

import com.revature.shms.enums.CleaningStatus;
import com.revature.shms.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {

    List<Room> findAllOrderByRoomNumberDesc();

    List<Room> findByStatus(CleaningStatus status);


}

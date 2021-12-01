package com.revature.shms.repositories;

import com.revature.shms.enums.ReservationStatus;
import com.revature.shms.models.Cleaning;
import com.revature.shms.models.Employee;
import com.revature.shms.models.Reservation;
import com.revature.shms.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    /**
     * gets Reservation by userId
     */
    Reservation findByUserReserveUserId(int id);

    /**
     * Gets all reservations
     */
    List<Reservation> findAll();

    /**
     * Gets Reservation by ReservationId.
     */
    Reservation findByReservationId(int id);

    /**
     * This deletes a reservation.
     */
    Reservation deleteByUserReserveUserId(int id);

}

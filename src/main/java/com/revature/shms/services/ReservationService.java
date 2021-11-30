package com.revature.shms.services;

import com.revature.shms.enums.ReservationStatus;
import com.revature.shms.models.Reservation;
import com.revature.shms.repositories.ReservationRepoistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class handles the reservation logic
 */
@Service
public class  ReservationService {
    @Autowired
    ReservationRepoistory reservationRepoistory;

    /**
     * Get a reservation with a userId
     * @param id
     * @return Reservation
     */
    public Reservation getReservationOfUser(String id){
        return reservationRepoistory.findByUserReserveUserId(Integer.parseInt(id));
    }

    /**
     * Get a reservation with a reservation Id
     * @param reservationId
     * @return Reservation
     */
    public Reservation getReservationWithReservationId(String reservationId){
        return reservationRepoistory.findByUserReserveUserId(Integer.parseInt(reservationId));

    }

    /**
     * Get all reservations
     * @return a list of reservations
     */
    public List<Reservation> getAllReservations(){
        return reservationRepoistory.findAll();
    }

    /**
     * This creates a reservation
     * @return Reservation.
     */
    public Reservation createReservation(Reservation reservation){
        return reservationRepoistory.save(reservation);
    }

    /**
     * This deletes a reservation by a userId
     */
    public Reservation deleteReservation(String id) {
        return reservationRepoistory.deleteByUserReserveUserId(Integer.parseInt(id));
    }

    /**
     * This toggles the reservation status
     */
    public Reservation changeStatusOfReservation(String id, Reservation reservation){
        return reservationRepoistory.save(reservation);
    }
    /**
     * This toggles the date
     */
    public Reservation changeDateOfReservation(String id, Reservation reservation){
        return reservationRepoistory.save(reservation);
    }
}

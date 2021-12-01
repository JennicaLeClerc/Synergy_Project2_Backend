package com.revature.shms.services;

import com.revature.shms.enums.ReservationStatus;
import com.revature.shms.models.Reservation;
import com.revature.shms.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class handles the reservation logic
 */
@Service
public class  ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    /**
     * Get a reservation with a userId
     * @param id
     * @return Reservation
     */
    public Reservation getReservationOfUser(String id){
        return reservationRepository.findByUserReserveUserId(Integer.parseInt(id));
    }

    /**
     * Get a reservation with a reservation Id
     * @param reservationId
     * @return Reservation
     */
    public Reservation getReservationWithReservationId(String reservationId){
        return reservationRepository.findByUserReserveUserId(Integer.parseInt(reservationId));

    }

    /**
     * Get all reservations
     * @return a list of reservations
     */
    public List <Reservation> getAll(){
 return reservationRepository.findAll();
    }


    /**
     * This creates a reservation
     * @return Reservation.
     */
    public Reservation createReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    /**
     * This deletes a reservation by a userId
     */
    public Reservation deleteReservation(String id) {
        return reservationRepository.deleteByUserReserveUserId(Integer.parseInt(id));
    }

    /**
     * This toggles the reservation status
     */
    public Reservation changeStatusOfReservation(String id, Reservation reservation){
        return reservationRepository.save(reservation);
    }
    /**
     * This toggles the date
     */
    public Reservation changeDateOfReservation(String id, Reservation reservation){
        return reservationRepository.save(reservation);
    }
}

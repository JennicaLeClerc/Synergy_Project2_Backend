package com.revature.shms.services;

import com.revature.shms.exceptions.EntityNotFound;
import com.revature.shms.models.Reservation;
import com.revature.shms.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class handles the reservation logic
 */
@Service
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class  ReservationService {
    @Autowired
	ReservationRepository reservationRepository;

    /**
     * Get a reservation with a userId
     * @param id
     * @return Reservation
     */
    public Reservation getReservationOfUser(String id) throws EntityNotFound {
        return reservationRepository.findByUserReserve_UserID(Integer.parseInt(id)).orElseThrow(EntityNotFound::new);
    }

    /**
     * Get a reservation with a reservation Id
     * @param reservationId
     * @return Reservation
     */
    public Reservation getReservationWithReservationId(String reservationId) throws EntityNotFound {
        return reservationRepository.findByReservationID(Integer.parseInt(reservationId)).orElseThrow(EntityNotFound::new);
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
        return reservationRepository.deleteByUserReserve_UserID(Integer.parseInt(id));
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

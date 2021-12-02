package com.revature.shms.services;

import com.revature.shms.enums.ReservationStatus;
import com.revature.shms.models.Reservation;
import com.revature.shms.models.User;
import com.revature.shms.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public Reservation getReservationOfUser(String id) throws NotFound {
        return reservationRepository.findByUserReserve_UserID(Integer.parseInt(id)).orElseThrow(NotFound::new);
    }

    /**
     * Get a reservation with a reservation Id
     * @param reservationId
     * @return Reservation
     */
    public Reservation getReservationWithReservationId(String reservationId) throws NotFound {
        return reservationRepository.findByReservationID(Integer.parseInt(reservationId)).orElseThrow(NotFound::new);
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
     * This toggles the reservation status for employees or users that cancel a reservation
     */
    public Reservation changeStatusOfReservation( Reservation reservation){
        return reservationRepository.save(reservation);
    }
    
    /**
     * This toggles the date
     */
    public Reservation changeDateOfReservation( Reservation reservation){
        return reservationRepository.save(reservation);
    }

    /**
     * The user can set a reservation to a specific date
     */
    public Reservation setReservation(User user, Date date){
        Reservation reservation = new Reservation();
        reservation.setUserReserve(user);
        reservation.setStatus(ReservationStatus.PENDING.toString());
        reservation.setDate(date);
        return reservationRepository.save(reservation);
    }

}

package com.revature.shms.services;

import com.revature.shms.enums.ReservationStatus;
import com.revature.shms.models.CustomReservation;
import com.revature.shms.models.Reservation;
import com.revature.shms.models.User;
import com.revature.shms.repositories.ReservationRepository;
import com.revature.shms.repositories.UserRepository;
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
    @Autowired
    UserRepository userRepository;
    /**
     * Get a reservation with a userId
     * @param id
     * @return Reservation
     */
    public Reservation findReservationOfUser(String id) throws NotFound {
        return reservationRepository.findByUserReserve_UserID(Integer.parseInt(id)).orElseThrow(NotFound::new);
    }

    /**
     * Get a reservation with a reservation Id
     * @param reservationId
     * @return Reservation
     */
    public Reservation findReservationWithReservationId(String reservationId) throws NotFound {
        return reservationRepository.findByReservationID(Integer.parseInt(reservationId)).orElseThrow(NotFound::new);
    }

    /**
     * Get all reservations
     * @return a list of reservations
     */
    public List <Reservation> findAll(){
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
    public void deleteReservation(int id) {
		reservationRepository.deleteByUserReserve_UserID(id);
    }

    /**
     * This toggles the reservation status for employees or users that cancel a reservation
     */
    public Reservation changeStatusOfReservation( CustomReservation customReservation) throws NotFound {
        Reservation reservation = findReservationOfUser(customReservation.getUserID());
        reservation.setStatus(ReservationStatus.valueOf(customReservation.getStatus()));
        return reservationRepository.save(reservation);
    }
    
    /**
     * This toggles the date
     */
    public Reservation changeDateOfReservation( CustomReservation customReservation) throws NotFound {
        Reservation reservation = findReservationOfUser(customReservation.getUserID());
        reservation.setStartDate(customReservation.getStartDate());
        reservation.setEndDate(customReservation.getEndDate());
        return reservationRepository.save(reservation);
    }

    /**
     * The user can set a reservation to a specific date
     */
    public Reservation setReservation(CustomReservation customReservation){
        User user = userRepository.findByUserID(Integer.parseInt(customReservation.getUserID())).get();
        Reservation reservation = new Reservation();
        reservation.setUserReserve(user);
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setStartDate(customReservation.getStartDate());
        reservation.setEndDate(customReservation.getEndDate());
        reservation.setAmenities(customReservation.getAmenities());
        return reservationRepository.save(reservation);
    }
}

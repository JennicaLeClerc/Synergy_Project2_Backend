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
     * @param userID
     * @return Reservation
     */
    public Reservation findReservationByUserID(int userID) throws NotFound {
        return reservationRepository.findByUserReserve_UserID(userID).orElseThrow(NotFound::new);
    }

    /**
     * Get a reservation with a reservation Id
     * @param reservationId
     * @return Reservation
     */
    public Reservation findReservationByReservationID(int reservationId) throws NotFound {
        return reservationRepository.findByReservationID(reservationId).orElseThrow(NotFound::new);
    }

    /**
     * Get all reservations
     * @return a list of reservations
     */
    public List <Reservation> findAll(){
        return reservationRepository.findAll();
    }

    /**
     * This creates or saves a reservation
     * @return Reservation.
     */
    public Reservation createReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

//    public Reservation approveReservation(int employeeId) {
//        return reservationRepository.approveReservationByEmployee_EmployeeId(employeeId);
//    }
//
//    public Reservation denyReservation(int employeeId){
//        return reservationRepository.denyReservationByEmployee_EmployeeId(employeeId);
//    }

    /**
     * This deletes a reservation by a userId
     */
    public void deleteReservationByUserID(int userID) {
		reservationRepository.deleteByUserReserve_UserID(userID);
    }

    /**
     * This toggles the reservation status for employees or users that cancel a reservation
     */
    public Reservation changeStatusOfReservation(Reservation reservation){
        return createReservation(reservation);
    } // currently doesn't do anything.
    
    /**
     * This toggles the date
     */
    public Reservation changeDateOfReservation( Reservation reservation){
        return createReservation(reservation);
    } // Currently doesn't do anything

    /**
     * Sets the accommodations of the given reservation to the given accommodations string.
     * @param reservationID the given reservationID.
     * @param accommodations the accommodations that are being added or changed to.
     * @return a Reservation with the given accommodations.
     */
    public Reservation setAccommodations(int reservationID, String accommodations) throws NotFound {
        Reservation reservation = findReservationByReservationID(reservationID);
        reservation.setAccommodations(accommodations);
        return createReservation(reservation);
    }

    /**
     * The user can set a reservation to a specific date
     */
    public Reservation setReservation(User user, String startDate, String endDate){
        //System.out.println(" start date is: " + startDate);
        Reservation reservation = new Reservation();
        reservation.setUserReserve(user);
        reservation.setStatus(ReservationStatus.PENDING.toString());
        //System.out.println(reservation.getUserReserve().getUsername());

        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        //System.out.println(reservation.getStartDate());
        //System.out.println(reservation.getEndDate());
        return createReservation(reservation);
    }
}

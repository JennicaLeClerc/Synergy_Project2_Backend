package com.revature.shms.services;

import com.revature.shms.enums.ReservationStatus;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    // -- Create/Delete
    /**
     * This creates or saves a reservation
     * @param reservation object that will be created.
     * @return Reservation object that was created .
     */
    public Reservation createReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    /**
     * This deletes a reservation by a userID.
     * @param userID of user that will have their reservation deleted.
     */
    public void deleteReservationByUserID(int userID) {
		reservationRepository.deleteByUserReserve_UserID(userID);
    }

    /**
     * This deletes a reservation by a reservationID.
     * @param reservationID of the reservation to be deleted.
     */
    public void deleteReservationByReservationID(int reservationID){
        reservationRepository.deleteByReservationID(reservationID);
    }

    // -- Sets
    /**
     * Handles the creation of a reservation and sets the default status as pending
     * @param reservation object that will be created
     * @return Reservation object that was created with a Pending status.
     */
    public Reservation setReservation(Reservation reservation){
        reservation.setStatus(ReservationStatus.PENDING);
        return reservationRepository.save(reservation);
    }

    /*public Reservation setReservation(User user, String startDate, String endDate) {
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
    }*/

    /**
     * Sets the accommodations of the given reservation to the given accommodations string.
     * @param reservationID of reservation that will be changed.
     * @param accommodations the accommodations that are being added or changed to.
     * @return a Reservation with the given accommodations.
     * @throws NotFound exception if a reservation with the given reservationID was not found.
     */
    public Reservation setAccommodations(int reservationID, String accommodations) throws NotFound {
        Reservation reservation = findReservationByReservationID(reservationID);
        reservation.setAccommodations(accommodations);
        return createReservation(reservation);
    }

    // -- Change
    /**
     * This toggles the reservation status for employees or users that cancel a reservation
     * @param reservationID of reservation that will be changed.
     * @param status that will be used to update
     * @return Reservation object that was updated
     * @throws NotFound exception if a reservation with the given reservationID was not found.
     */
    public Reservation changeStatusOfReservation(int reservationID, ReservationStatus status) throws NotFound {
        Reservation reservation = findReservationByReservationID(reservationID);
        reservation.setStatus(status);
        return reservationRepository.save(reservation);
    }

    /**
     * This toggles the date
     * @param reservationID of reservation that will be changed.
     * @param startDate start date that will be updated
     * @param endDate end date that will be updated
     * @return Reservation object with updated fields
     * @throws NotFound exception if a reservation with the given reservationID was not found.
     */
    public Reservation changeDateOfReservation(int reservationID, String startDate, String endDate) throws NotFound {
        Reservation reservation = findReservationByReservationID(reservationID);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        return reservationRepository.save(reservation);
    }

    // -- Finds
    /**
     * Get all reservations
     * @return a list of reservations
     */
    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    /**
     * Get a reservation with a userId
     * @param userID of user that created a reservation.
     * @return Reservation object of the specific user.
     * @throws NotFound exception if reservation not found.
     */
    public List<Reservation> findReservationByUserID(int userID) throws NotFound {
        return reservationRepository.findByUserReserve_UserID(userID).orElseThrow(NotFound::new);
    }

    /**
     * Get a reservation with a reservation Id
     * @param reservationId specific to a reservation
     * @return Reservation object of the reservation id.
     * @throws NotFound exception id reservation not found.
     */
    public Reservation findReservationByReservationID(int reservationId) throws NotFound {
        return reservationRepository.findByReservationID(reservationId).orElseThrow(NotFound::new);
    }


    public List<Reservation> getAllReservationsOfUser (String username){
        User user = userRepository.findByUsername(username).get();
        return reservationRepository.findByUserReserve_UserID(user.getUserID()).get();
    }

    public List<Reservation> getAllPendingStatus(){
        return reservationRepository.findAllByStatus(ReservationStatus.PENDING).get();
    }

    public List<Reservation> getAllApprovedAndStartDate() throws ParseException {
        List<Reservation> reservations;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        now.plusDays(5);
        Date todayPlusFive = new SimpleDateFormat("yyyy/MM/dd").parse(dtf.format(now));
        reservations = reservationRepository.findAllByStatus(ReservationStatus.APPROVED).get();
        List<Reservation> correctReservations = new ArrayList<>();
        reservations.forEach(
                reservation -> {
                    try {
                        Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(reservation.getStartDate());
                        if (date1.compareTo(todayPlusFive) < 0){
                            correctReservations.add(reservation);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
        );
    return correctReservations;
    }

    public List<Reservation> getAllApprovedAndStartandEnd() throws ParseException {
        List<Reservation> reservations;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        Date today = new SimpleDateFormat("yyyy/MM/dd").parse(dtf.format(now));
        reservations = reservationRepository.findAllByStatus(ReservationStatus.APPROVED).get();
        List<Reservation> correctReservations = new ArrayList<>();
        reservations.forEach(
                reservation -> {
                    try {
                        Date start = new SimpleDateFormat("yyyy/MM/dd").parse(reservation.getStartDate());
                        Date end = new SimpleDateFormat("yyyy/MM/dd").parse(reservation.getEndDate());
                        if (start.compareTo(today) < 0 && end.compareTo(today) > 0 ){
                            correctReservations.add(reservation);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
        );
        return correctReservations;
    }



}

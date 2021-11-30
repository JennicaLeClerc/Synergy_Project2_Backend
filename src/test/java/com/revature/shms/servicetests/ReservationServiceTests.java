package com.revature.shms.servicetests;

import com.revature.shms.enums.ReservationStatus;
import com.revature.shms.models.Reservation;
import com.revature.shms.models.User;
import com.revature.shms.services.ReservationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;



@ExtendWith(MockitoExtension.class)
public class ReservationServiceTests {
    ReservationService reservationService;

    @Test
    public void returnAllReservationsTest(){
        List<Reservation> test;
        test = reservationService.getAllReservations();
        test.stream().forEach(reservation -> System.out.println(reservation));
    }

    @Test
    public void getSpecificReservation() {
       String id = "1";
       Reservation reservation =reservationService.getReservationOfUser(id);
       User user = reservation.getUserReserve();
        Assertions.assertEquals(id, user.getUserId());
    }

    @Test
    public void getSpecificReservationWithId() {
        String id = "1";
        Reservation reservation =reservationService.getReservationWithReservationId(id);
        int reservationId = reservation.getReservationId();
        Assertions.assertEquals(id, reservationId);
    }

    @Test
    public void createUserTest(){
        Reservation reservation = new Reservation();
        reservation.setStatus(ReservationStatus.ACTIVE.toString());
        Reservation result =reservationService.createReservation(reservation);
        Assertions.assertEquals(result.getStatus(), reservation.getStatus());
    }

    @Test
    public void deleteUserTest(){
        Reservation reservation = new Reservation();
        reservationService.createReservation(reservation);
        System.out.println(reservation.getReservationId());
        Integer idToDelete = reservation.getReservationId();
        reservationService.deleteReservation(idToDelete.toString());
        Assertions.assertNull(reservationService.getReservationWithReservationId(idToDelete.toString()));
    }

    @Test void changeStatusOfReservationTest(){
        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        Reservation update = new Reservation();
        reservation.setStatus(ReservationStatus.ACTIVE.toString());
        update.setStatus(ReservationStatus.CANCELLED.toString());
        reservationService.changeStatusOfReservation("1", update);
        Assertions.assertEquals(reservation.getStatus(), ReservationStatus.CANCELLED.toString());
    }
}

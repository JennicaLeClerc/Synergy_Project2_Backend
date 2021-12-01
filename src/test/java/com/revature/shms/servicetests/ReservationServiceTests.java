package com.revature.shms.servicetests;

import com.revature.shms.enums.Amenities;
import com.revature.shms.enums.ReservationStatus;
import com.revature.shms.models.AmenityWrapper;
import com.revature.shms.models.Reservation;
import com.revature.shms.models.User;
import com.revature.shms.repositories.ReservationRepoistory;
import com.revature.shms.services.ReservationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTests {

    @InjectMocks
    ReservationService reservationService ;
    @Mock
    ReservationRepoistory reservationRepository;

    /**
     *
     */
    @Test
    public void returnAllReservationsTest(){
        List<Reservation> test = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        test.add(reservation);
        System.out.println(test.isEmpty());
        System.out.println(reservation.getReservationId());
        when(reservationRepository.findAll()).thenReturn(test);
        assertEquals(reservationService.getAll(),test);
    }

    @Test
    public void getSpecificReservation() {
       int id = 1;
       Reservation reservation = new Reservation();
       reservation.setReservationId(id);
       when(reservationRepository.findByUserReserveUserId(1)).thenReturn(reservation);
        assertEquals(reservationService.getReservationOfUser("1").getReservationId(), id);
    }

    @Test
    public void createUserTest(){
        Reservation reservation = new Reservation();
        reservation.setStatus(ReservationStatus.ACTIVE.toString());
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        Assertions.assertEquals(reservationService.createReservation(reservation), reservation);
    }

    @Test
    public void deleteUserTest(){
        Reservation reservation = new Reservation();
        System.out.println(reservation.getReservationId());
        Integer idToDelete = reservation.getReservationId();
        when(reservationRepository.deleteByUserReserveUserId(idToDelete)).thenReturn(reservation);
        Assertions.assertEquals(reservationService.deleteReservation(idToDelete.toString()), reservation);
    }

//    @Test void changeStatusOfReservationTest(){
//        Reservation reservation = new Reservation();
//        reservation.setReservationId(1);
//        Reservation update = new Reservation();
//        reservation.setStatus(ReservationStatus.ACTIVE.toString());
//        update.setStatus(ReservationStatus.CANCELLED.toString());
//        when(reservationRepository.save(reservation)).thenReturn(reservation);
//        Assertions.assertEquals( reservationService.changeStatusOfReservation("1", update).getStatus(),reservation.getStatus());
//    }
}

package com.revature.shms.servicetests;

import com.revature.shms.enums.ReservationStatus;
import com.revature.shms.models.CustomReservation;
import com.revature.shms.models.Reservation;
import com.revature.shms.models.User;
import com.revature.shms.repositories.ReservationRepository;
import com.revature.shms.repositories.UserRepository;
import com.revature.shms.services.ReservationService;
import com.revature.shms.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTests {
	@InjectMocks ReservationService reservationService ;
	@Mock ReservationRepository reservationRepository;

	@InjectMocks UserService userService ;
	@Mock UserRepository userRepository;

	@Test
	public void returnAllReservationsTest(){
		List<Reservation> test = new ArrayList<>();
		Reservation reservation = new Reservation();
		reservation.setReservationID(1);
		test.add(reservation);
		when(reservationRepository.findAll()).thenReturn(test);
		assertEquals(test, reservationService.findAll());
	}

	@Test
	public void getSpecificReservation() throws NotFound {
		int id = 1;
		Reservation reservation = new Reservation();
		reservation.setReservationID(id);
		when(reservationRepository.findByUserReserve_UserID(1)).thenReturn(java.util.Optional.of(reservation));
		assertEquals(id, reservationService.findReservationOfUser("1").getReservationID());
	}

	@Test
	public void createUserTest(){
		Reservation reservation = new Reservation();
		reservation.setStatus(ReservationStatus.APPROVED);
		when(reservationRepository.save(reservation)).thenReturn(reservation);
		assertEquals(reservation, reservationService.createReservation(reservation));
	}

	@Test
	public void deleteUserTest(){
		reservationService.deleteReservation(1231245);
		verify(reservationRepository,times(1)).deleteByUserReserve_UserID(anyInt());
	}

    @Test void changeStatusOfReservationTest() throws NotFound {
        Reservation reservation = new Reservation();
		CustomReservation update = new CustomReservation();
		update.setUserID("1");
		update.setStatus(ReservationStatus.REJECTED.toString());
        reservation.setReservationID(1);
        reservation.setStatus(ReservationStatus.APPROVED);
        update.setStatus(ReservationStatus.CANCELLED.toString());
		when(reservationRepository.findByUserReserve_UserID(1)).thenReturn(java.util.Optional.of(reservation));
		when(reservationRepository.save(any())).thenReturn(reservation);
		assertEquals(reservation, reservationService.changeStatusOfReservation(update));
    }

	@Test void changeDateOfReservationTest() throws NotFound {
		Reservation reservation = new Reservation();
		reservation.setReservationID(1);
		CustomReservation update = new CustomReservation();
		update.setUserID("1");
		reservation.setStatus(ReservationStatus.APPROVED);
		update.setStatus(ReservationStatus.CANCELLED.toString());
		when(reservationRepository.findByUserReserve_UserID(1)).thenReturn(java.util.Optional.of(reservation));
		when(reservationRepository.save(any())).thenReturn(reservation);
		assertEquals(reservation, reservationService.changeDateOfReservation(update));
	}

	@Test void getReservationWithReservationIdTest () throws NotFound {
		int id = 1;
		Reservation reservation = new Reservation();
		reservation.setReservationID(id);
		when(reservationRepository.findByReservationID(1)).thenReturn(java.util.Optional.of(reservation));
		assertEquals(id, reservationService.findReservationWithReservationId("1").getReservationID());
	}

	@Test
	public void reserveTest(){
		Reservation reservation = new Reservation();
		CustomReservation customReservation = new CustomReservation();
		String date = "12/11/1997";
		Optional<String> strin = Optional.of("1");
		customReservation.setUserID(strin.get());
		customReservation.setStartDate("1");
		customReservation.setEndDate("1");
		customReservation.setAmenities("1");
		User user = new User();
		when(userRepository.findByUserID(Integer.parseInt(customReservation.getUserID()))).thenReturn(Optional.of(user));
		when(reservationRepository.save(any())).thenReturn(reservation);
		assertEquals(reservation, reservationService.setReservation(customReservation));

	}

	@Test
	public void gettersSetters(){
		ReservationRepository reservationRepository = null;
		ReservationService reservationService = new ReservationService();
		reservationService.setReservationRepository(reservationRepository);
		assertNull(reservationService.getReservationRepository());
		reservationService.setReservationRepository(null);
	}
}

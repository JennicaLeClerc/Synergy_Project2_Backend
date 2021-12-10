package com.revature.shms.repositories;

import com.revature.shms.enums.ReservationStatus;
import com.revature.shms.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer > {
	Optional<List<Reservation>>  findByUserReserve_UserID(int userID);
	Optional<Reservation> findByReservationID(int reservationID);

	void deleteByUserReserve_UserID(int userID);
	void deleteByReservationID(int reservationID);

	Optional<List<Reservation>> findAllByStatus(ReservationStatus status);
}

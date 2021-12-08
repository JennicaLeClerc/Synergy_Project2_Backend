package com.revature.shms.repositories;

import com.revature.shms.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer > {
	Optional<Reservation>  findByUserReserve_UserID(int id);
	Optional<Reservation> findByReservationID(int id);

	void deleteByUserReserve_UserID(int id);
}

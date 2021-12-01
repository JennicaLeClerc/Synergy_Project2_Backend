package com.revature.shms.repositories;

import com.revature.shms.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepoistory extends JpaRepository<Reservation,Integer > {

}

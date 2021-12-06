package com.revature.shms.controllers;

import com.revature.shms.models.CustomReservation;
import com.revature.shms.models.Reservation;
import com.revature.shms.models.User;
import com.revature.shms.repositories.ReservationRepository;
import com.revature.shms.repositories.UserRepository;
import com.revature.shms.services.ReservationService;
import com.revature.shms.services.UserService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservationService reservationService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    /**
     * Employees can view all reservations here
     * @return
     */
    @GetMapping("/all")
    public Page<Reservation> returnAllReservations(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestParam("sortBy") String sortBy){
        return reservationRepository.findAll(PageRequest.of(pageNumber,pageSize, Sort.by(sortBy)));
    }

    /**
     * An employee can set the status of a reservation to accepted or rejected.
     * @param customReservation to change
     * @return
     * @throws NotFound
     */
    @PostMapping("/update")
    public ResponseEntity<?> setStatusOfReservation(@RequestBody CustomReservation customReservation) throws NotFound {
        System.out.println("Request recieved");
        Reservation reservation = reservationService.findReservationOfUser(customReservation.getUserID());
        reservation.setStatus(customReservation.getStatus());
        return ResponseEntity.ok(reservationService.changeStatusOfReservation(reservation));
    }
    /**
     * A user can send a psot reuquest with the start and end date of a reservation.
     * @param customReservation
     * @return
     * @throws NotFound
     */
    @PostMapping("/save")
    public ResponseEntity<?> createNewReservation(@RequestBody CustomReservation customReservation) throws NotFound {
        System.out.println("Request recieved");

        User user = userRepository.findByUserID(Integer.parseInt(customReservation.getUserID())).get();

        return  ResponseEntity.ok( reservationService.setReservation(user, customReservation.getStartDate(), customReservation.getEndDate()));
    }
}

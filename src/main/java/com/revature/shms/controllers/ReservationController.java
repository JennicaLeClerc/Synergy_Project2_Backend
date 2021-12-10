package com.revature.shms.controllers;

import com.revature.shms.enums.ReservationStatus;
import com.revature.shms.models.Reservation;
import com.revature.shms.repositories.ReservationRepository;
import com.revature.shms.repositories.UserRepository;
import com.revature.shms.services.ReservationService;
import com.revature.shms.services.UserService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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

    @GetMapping("/all")
    public Page<Reservation> returnAllReservations(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestParam("sortBy") String sortBy){
        return reservationRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> setStatusOfReservation(@PathVariable("id") int reservationID, @RequestParam("status") ReservationStatus status) throws NotFound {
           return ResponseEntity.ok(reservationService.changeStatusOfReservation(reservationID, status));
    }

    @PostMapping("/save")
    public ResponseEntity<?> createNewReservation(@RequestBody Reservation reservation) throws NotFound {
        return  ResponseEntity.ok(reservationService.setReservation(reservation));
    }

    @PostMapping("/update/dates/{id}")
    public ResponseEntity<?> setDateReservation(@PathVariable("id") int reservationID, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws NotFound {
        return ResponseEntity.ok(reservationService.changeDateOfReservation(reservationID, startDate, endDate));
    }

    @PostMapping("/username")
    public ResponseEntity<?> getReservationsByUsername(@RequestParam("username") String username) {
        return ResponseEntity.ok(reservationService.getAllReservationsOfUser(username));
    }

    @GetMapping("/status/pending")
    public ResponseEntity<?> getAllPending(){
        return ResponseEntity.ok(reservationService.getAllPendingStatus());
    }

    @GetMapping("/start")
    public ResponseEntity<?> startDateLessToday() throws ParseException {
        return ResponseEntity.ok(reservationService.getAllApprovedAndStartDate());
    }

    @GetMapping("/startend")
    public ResponseEntity<?> startDateAndEndDate() throws ParseException {
        return ResponseEntity.ok(reservationService.getAllApprovedAndStartandEnd());
    }

}

package com.revature.shms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Reservation model
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int reservationID;

    @Column
    String status;

    @Column
    String startDate;

    @Column
    String endDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private User userReserve;

}

package com.revature.shms.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This custom object allows for mapping the request of the api call to an object for use on the reservation call.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomReservation {
    String userID;
    String startDate;
    String status;
    String endDate;
}

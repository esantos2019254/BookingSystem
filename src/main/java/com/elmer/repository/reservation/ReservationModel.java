package com.elmer.repository.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationModel {
    private Long id;
    private Long userId;
    private Date reservationDate;
    private String reservationDetails;
}

package com.elmer.model.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationModel {
    @Id
    private String id;
    private Long userId;
    private Date reservationDate;
    private String reservationDetails;
}

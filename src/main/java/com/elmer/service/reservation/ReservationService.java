package com.elmer.service.reservation;

import com.elmer.repository.reservation.ReservationModel;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    ReservationModel createReservation(ReservationModel reservation);

    List<ReservationModel> getAllReservation();

    Optional<ReservationModel> getReservationById(Long id);

    ReservationModel updateReservation(ReservationModel reservation, Long id);

    void deleteReservation(Long id);
}

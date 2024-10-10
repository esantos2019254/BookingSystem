package com.elmer.service.reservation;

import com.elmer.repository.reservation.ReservationModel;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationServiceMap implements ReservationService {

    private final Map<Long, ReservationModel> reservations = new HashMap<>();
    private Long idCounter = 0L;

    @Override
    public ReservationModel createReservation(ReservationModel reservation) {
        reservation.setId(++idCounter);
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    @Override
    public List<ReservationModel> getAllReservation() {
        return new ArrayList<>(reservations.values());
    }

    @Override
    public Optional<ReservationModel> getReservationById(Long id) {
        return Optional.ofNullable(reservations.get(id));
    }

    @Override
    public ReservationModel updateReservation(ReservationModel reservation, Long id) {
        reservations.put(id, reservation);
        return reservation;
    }

    @Override
    public void deleteReservation(Long id) {
        reservations.remove(id);
    }
}

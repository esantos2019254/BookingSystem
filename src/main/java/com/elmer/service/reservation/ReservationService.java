package com.elmer.service.reservation;

import com.elmer.model.reservation.ReservationModel;
import com.elmer.repository.reservation.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) { this.reservationRepository = reservationRepository; }

    public ReservationModel save(ReservationModel reservation){ return reservationRepository.save(reservation); }
    public List<ReservationModel> getAllReservations(){ return reservationRepository.findAll(); }
    public Optional<ReservationModel> getReservationById(String id){ return reservationRepository.findById(id); }
    public void deleteReservation(String id){ reservationRepository.deleteById(id); }

    public ReservationModel updateReservation(ReservationModel reservation, String id){
        return reservationRepository.findById(id).map(existingReservation ->{
            existingReservation.setUserId(reservation.getUserId());
            existingReservation.setReservationDate(reservation.getReservationDate());
            existingReservation.setReservationDetails(reservation.getReservationDetails());
            return reservationRepository.save(existingReservation);
        }).orElse(null);
    }
}
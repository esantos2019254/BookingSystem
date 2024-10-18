package com.elmer.controller;

import com.elmer.model.reservation.ReservationModel;
import com.elmer.service.reservation.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) { this.reservationService = reservationService; }

    @PostMapping
    public ResponseEntity<ReservationModel> createReservation(@RequestBody ReservationModel reservation){
        reservationService.save(reservation);
        log.info("Reservation creada exitosamente: {}", reservation);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ReservationModel>> getAllReservations(){
        List<ReservationModel> listReservation = reservationService.getAllReservations();
        return ResponseEntity.ok(listReservation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationModel> getReservationById(@PathVariable("id") String id){
        return reservationService.getReservationById(id).map(reservation -> {
            log.info("Reservación encontrada: {}", reservation);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") String id){
        Optional<ReservationModel> reservation = reservationService.getReservationById(id);
        if(reservation.isPresent()){
            reservationService.deleteReservation(id);
            log.info("Rerservation eliminada exitosamente: {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationModel> updateReservation(@RequestBody ReservationModel reservationModel, @PathVariable("id") String id){
        ReservationModel reservation = reservationService.updateReservation(reservationModel, id);
        if(reservation!=null){
            log.info("Reservación actualizada {}", reservation);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
package com.elmer.controller;

import com.elmer.model.reservation.ReservationModel;
import com.elmer.service.reservation.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateReservation() {
        ReservationModel reservation = new ReservationModel();
        when(reservationService.save(any(ReservationModel.class))).thenReturn(reservation);
        ResponseEntity<ReservationModel> response = reservationController.createReservation(reservation);
        verify(reservationService, times(1)).save(any(ReservationModel.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetReservationByIdNotFound() {
        String id = "123";
        when(reservationService.getReservationById(id)).thenReturn(Optional.empty());
        ResponseEntity<ReservationModel> response = reservationController.getReservationById(id);
        verify(reservationService, times(1)).getReservationById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}

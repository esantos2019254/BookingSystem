package com.elmer.repository.reservation;

import com.elmer.model.reservation.ReservationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends MongoRepository<ReservationModel, String> {
}

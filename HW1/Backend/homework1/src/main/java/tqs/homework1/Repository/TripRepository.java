package tqs.homework1.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tqs.homework1.Model.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAll();
    List<Trip> findByOriginAndDestinationAndTripDate(String origin, String destination, Date tripDate);
    Trip findById(long id);
    List<Trip> deleteByTripDateBefore(Date date);
}
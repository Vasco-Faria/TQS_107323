package tqs.homework1.RepositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import tqs.homework1.Model.Trip;
import tqs.homework1.Repository.TripRepository;

@DataJpaTest
public class TripRepositoryTests {

    @Autowired
    private TripRepository tripRepository;

    @Test
    public void testFindAll() {
       
        Trip trip1 = new Trip();
        Trip trip2 = new Trip();
        tripRepository.saveAll(List.of(trip1, trip2));

       
        List<Trip> trips = tripRepository.findAll();

        
        assertEquals(2, trips.size());
    }

    @Test
    public void testFindByOriginAndDestinationAndTripDate() {
       
        Trip trip = new Trip();
        trip.setOrigin("Origem");
        trip.setDestination("Destino");
        trip.setTripDate(new Date());
        tripRepository.save(trip);

        
        List<Trip> foundTrips = tripRepository.findByOriginAndDestinationAndTripDate("Origem", "Destino", new Date());

       
        assertEquals(1, foundTrips.size());
        assertEquals("Origem", foundTrips.get(0).getOrigin());
        assertEquals("Destino", foundTrips.get(0).getDestination());
    }

    @Test
    public void testFindById() {
      
        Trip trip = new Trip();
        tripRepository.save(trip);

       
        Optional<Trip> foundTripOptional = tripRepository.findById(trip.getId());

       
        assertTrue(foundTripOptional.isPresent());
        assertEquals(trip.getId(), foundTripOptional.get().getId());
    }

    @Test
    public void testDeleteByTripDateBefore() {
       
        Date today = new Date();
        Date yesterday = new Date(today.getTime() - 1000 * 60 * 60 * 24); 

        Trip trip1 = new Trip();
        trip1.setTripDate(yesterday);
        tripRepository.save(trip1);

        Trip trip2 = new Trip();
        trip2.setTripDate(today);
        tripRepository.save(trip2);

        
        List<Trip> deletedTrips = tripRepository.deleteByTripDateBefore(today);

       
        assertEquals(1, deletedTrips.size());

       
        assertFalse(tripRepository.findById(trip1.getId()).isPresent());
    }

    @Test
    public void testFindByTripDate() {
        
        Date yesterday = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
        Date today = new Date();

        Trip trip1 = new Trip();
        trip1.setTripDate(yesterday);
        tripRepository.save(trip1);

        Trip trip2 = new Trip();
        trip2.setTripDate(today);
        tripRepository.save(trip2);

        
        List<Trip> foundTrips = tripRepository.findByTripDate(yesterday);

       
        assertEquals(1, foundTrips.size());
        assertEquals(trip1.getId(), foundTrips.get(0).getId());
    }

    @Test
    public void testDeleteByTripDate() {
      
        Date yesterday = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
        Trip trip = new Trip();
        trip.setTripDate(yesterday);
        tripRepository.save(trip);


        tripRepository.deleteByTripDate(yesterday);

       
        assertFalse(tripRepository.findById(trip.getId()).isPresent());
    }

    @Test
    public void testFindTopByOrderByTripDateDesc() {
        Date yesterday = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
        Date today = new Date();

        Trip trip1 = new Trip();
        trip1.setTripDate(yesterday);
        tripRepository.save(trip1);

        Trip trip2 = new Trip();
        trip2.setTripDate(today);
        tripRepository.save(trip2);

        
        Trip foundTrip = tripRepository.findTopByOrderByTripDateDesc();

       
        assertNotNull(foundTrip);
        assertEquals(trip2.getId(), foundTrip.getId());
    }
}
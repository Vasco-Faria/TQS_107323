package tqs.homework1.ServiceTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tqs.homework1.Model.Trip;
import tqs.homework1.Repository.TripRepository;
import tqs.homework1.Service.TripService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripService tripService;

    @Test
    @DisplayName("Test for trips in between two major cities in a choosed day")
void findTripsByOriginDestinationAndDate() {

    String origin = "Origin";
    String destination = "Destination";
    Date tripDate = new Date();
    List<Trip> expectedTrips = new ArrayList<>();
    Trip trip1 = new Trip("Aveiro", "Barcelona", "Company", "10:00", "12:00", 50.0);
    trip1.setTripDate(tripDate);
    expectedTrips.add(trip1);
    Trip trip2 = new Trip("Barcelona", "Barcelona", "Company", "14:00", "16:00", 60.0);
    trip2.setTripDate(tripDate);
    expectedTrips.add(trip2);

   
    when(tripRepository.findByOriginAndDestinationAndTripDate(origin, destination, tripDate))
            .thenReturn(expectedTrips);

  
    List<Trip> actualTrips = tripService.findTripsByOriginDestinationAndDate(origin, destination, tripDate);

   
    assertEquals(expectedTrips.size(), actualTrips.size());
    for (int i = 0; i < expectedTrips.size(); i++) {
        assertEquals(expectedTrips.get(i), actualTrips.get(i));
    }
}
}

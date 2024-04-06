package tqs.homework1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.homework1.Model.Trip;
import tqs.homework1.Repository.TripRepository;

import java.util.Date;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public List<Trip> findTripsByOriginDestinationAndDate(String origin, String destination, Date tripDate) {
        return tripRepository.findByOriginAndDestinationAndTripDate(origin, destination, tripDate);
    }
}
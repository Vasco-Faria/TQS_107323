package tqs.homework1.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tqs.homework1.Model.Trip;
import tqs.homework1.Repository.TripRepository;
import tqs.homework1.Service.TicketService;
import tqs.homework1.Service.TripService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


@RestController
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripService tripService;

    
    @GetMapping("/alltrips")
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    
    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> getTripsByOriginAndDestination(@RequestParam String origin, 
                                                                      @RequestParam String destination, 
                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Date sqlDate = java.sql.Date.valueOf(date);
        List<Trip> trips = tripService.findTripsByOriginDestinationAndDate(origin, destination, sqlDate);
        if (trips.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se nenhuma viagem for encontrada
        }
        return ResponseEntity.ok(trips);
    }

    
}

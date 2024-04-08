package tqs.homework1.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.homework1.Model.Ticket;
import tqs.homework1.Model.Trip;
import tqs.homework1.Repository.TicketRepository;
import tqs.homework1.Repository.TripRepository;
import tqs.homework1.Requests.PurchaseRequest;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TripRepository tripRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, TripRepository tripRepository) {
        this.ticketRepository = ticketRepository;
        this.tripRepository = tripRepository;
    }

      public void createTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

      public Iterable<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketInfoById(long id) {
      return ticketRepository.findById(id);
  }

  public void addTripToTicket(Long ticketId, Long tripId) {
    try {
        Optional<Trip> optionalTrip = tripRepository.findById(tripId);
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

        if (optionalTrip.isPresent()) {
            Trip trip = optionalTrip.get();

            if (optionalTicket.isPresent()) {
                Ticket ticket = optionalTicket.get();
                
                if (trip.getAvailableSeats() > 0) {
                  
                    String seat = generateUniqueSeat(trip);
                    
                    
                    trip.setAvailableSeats(trip.getAvailableSeats() - 1);
                    tripRepository.save(trip);

                    ticket.setTrip(trip);
                    ticket.setSeat(seat);
                    ticketRepository.save(ticket);
                } else {
                    throw new IllegalStateException("No available seats for trip with id " + tripId);
                }
            } else {
                throw new IllegalArgumentException("Ticket with id " + ticketId + " not found.");
            }
        } else {
            throw new IllegalArgumentException("Trip with id " + tripId + " not found.");
        }
    } catch (Exception e) {
        throw new RuntimeException("Failed to add trip to ticket.", e);
    }
}

private String generateUniqueSeat(Trip trip) {
    String seat;
    do {
        seat = generateSeat();
    } while (ticketRepository.existsByTripAndSeat(trip, seat)); 
    return seat;
}

private String generateSeat() {
   
    char row = (char) ('A' + (int) (Math.random() * 10));
   
    int seatNumber = (int) (1 + Math.random() * 4);
    
    return String.format("%c%d", row, seatNumber);
}

public void purchaseTicket(PurchaseRequest request) {
    Long ticketId = request.getTicketId();

    Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
    if (optionalTicket.isPresent()) {
        Ticket ticket = optionalTicket.get();
        ticket.setName(request.getName());
        ticket.setAddress(request.getAddress());
        ticket.setCity(request.getCity());
        ticket.setState(request.getState());
        ticket.setZipCode(request.getZipCode());
        ticket.setCardType(request.getCardType());
        ticket.setCreditnumber(request.getCreditCardNumber());
        ticket.setMonth(request.getMonth());
        ticket.setYear(request.getYear());
        ticket.setCardName(request.getNameOnCard());
        
        ticketRepository.save(ticket);
    } else {
        throw new IllegalArgumentException("Ticket with ID " + ticketId + " not found.");
    }
}


    public void deleteAllTickets() {
    
        ticketRepository.deleteAll();
    }

    public void deleteTicketById(Long ticketId) {
        
        ticketRepository.deleteById(ticketId);
    }
}





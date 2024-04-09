package tqs.homework1.ServiceTests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tqs.homework1.Model.Ticket;
import tqs.homework1.Model.Trip;
import tqs.homework1.Repository.TicketRepository;
import tqs.homework1.Repository.TripRepository;
import tqs.homework1.Requests.PurchaseRequest;
import tqs.homework1.Service.TicketService;

public class TicketServiceTests {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Ticket ticket = new Ticket();
            ticket.setId((long) i);
            tickets.add(ticket);
        }
        when(ticketRepository.findAll()).thenReturn(tickets);
    }

    

    
    @Test
    void createTicket() {
        Ticket ticket = new Ticket();
        ticketService.createTicket(ticket);
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void getAllTickets() {
        ticketService.getAllTickets();
        verify(ticketRepository, times(1)).findAll();
    }

    // @Test
    // void getTicketInfoById() {
    //     Long id = 5L;
        
    //     Ticket ticket = new Ticket();
    //     ticket.setId(id);
    //     ticket.setOrigin("Aveiro");
    //     ticket.setDestiny("Madrid");
        
        
    //     when(ticketRepository.findById(id)).thenReturn(ticket);
        
        
    //     Ticket retrievedTicket = ticketService.getTicketInfoById(id);
        
       
    //     assertEquals(ticket, retrievedTicket);
    // }







    @Test
    void addTripToTicket() {
        Long ticketId = 1L;
        Long tripId = 2L;

        Trip trip = new Trip();
        trip.setAvailableSeats(1);
        Ticket ticket = new Ticket();

        Optional<Trip> optionalTrip = Optional.of(trip);
        Optional<Ticket> optionalTicket = Optional.of(ticket);

        when(tripRepository.findById(tripId)).thenReturn(optionalTrip);
        when(ticketRepository.findById(ticketId)).thenReturn(optionalTicket);

        ticketService.addTripToTicket(ticketId, tripId);

        assertEquals(0, trip.getAvailableSeats());
        assertNotNull(ticket.getTrip());
        assertNotNull(ticket.getSeat());
        verify(tripRepository, times(1)).save(trip);
        verify(ticketRepository, times(1)).save(ticket);
    }


    // @Test
    // void testPurchaseTicket() {
    //     PurchaseRequest request = new PurchaseRequest();
    //     request.setTicketId(4L);
    //     request.setName("John Doe");

    //     Ticket ticket = new Ticket();
    //     ticket.setId(4L);

        
    //     when(ticketRepository.findById(4L)).thenReturn(ticket);

    //     when(ticketRepository.existsByTripAndSeat(any(), any())).thenReturn(false);

    //     ticketService.purchaseTicket(request);

     
    //     verify(ticketRepository).save(ticket);

        
    //     assertEquals("John Doe", ticket.getName());
        
    //     assertNotNull(ticket.getAddress());
    //     assertEquals("some_city", ticket.getCity());
       
    // }


    

    @Test
    void deleteAllTickets() {
        ticketService.deleteAllTickets();
        verify(ticketRepository, times(1)).deleteAll();
    }

    @Test
    void deleteTicketById() {
        Long ticketId = 1L;
        ticketService.deleteTicketById(ticketId);
        verify(ticketRepository, times(1)).deleteById(ticketId);
    }
}

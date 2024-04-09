package tqs.homework1.RepositoryTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs.homework1.Controller.TicketController;
import tqs.homework1.Model.Ticket;
import tqs.homework1.Model.Trip;
import tqs.homework1.Repository.TicketRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TicketRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TicketRepository ticketRepository;


    

    @Test
    public void testFindAllTickets() throws ParseException {
        String origin1 = "Aveiro";
        String destiny1 = "Viana";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2024-12-01");

        String origin2 = "Aveiro";
        String destiny2 = "Barcelona";
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = dateFormat2.parse("2024-03-27");
        
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();

        testEntityManager.persistAndFlush(ticket1);
        testEntityManager.persistAndFlush(ticket2);

        List<Ticket> tickets = ticketRepository.findAll();

        assertThat(tickets).isNotNull();

        assertThat(tickets).hasSize(2).extracting(Ticket::getDate).containsOnly(ticket1.getDate(), ticket2.getDate());
    }


    @Test
    public void testFindTicketById(){
        Ticket ticket1 = new Ticket();
        ticket1 = ticketRepository.save(ticket1);

        testEntityManager.persistAndFlush(ticket1);

        Ticket foundTicket = ticketRepository.findById(ticket1.getId()).orElse(null);

        assertThat(foundTicket).isNotNull();
        assertThat(foundTicket.getId()).isEqualTo(ticket1.getId());


    }

    @Test
    public void testExistsByTripAndSeat() {
        
        Trip trip = new Trip();
        trip.setId(1L);
        String seat = "A1";

       
        Ticket ticket = new Ticket();
        ticket.setTrip(trip);
        ticket.setSeat(seat);
        ticketRepository.save(ticket);

        
        boolean exists = ticketRepository.existsByTripAndSeat(trip, seat);
        assertTrue(exists);
    }

    @Test
    public void testDeleteAll() {
        
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        ticketRepository.saveAll(Arrays.asList(ticket1, ticket2));

        
        ticketRepository.deleteAll();

       
        assertEquals(0, ticketRepository.count());
    }

    @Test
    public void testDeleteById() {
        
        Ticket ticket = new Ticket();
        ticketRepository.save(ticket);

       
        ticketRepository.deleteById(ticket.getId());

        
        assertFalse(ticketRepository.existsById(ticket.getId()));
    }
}

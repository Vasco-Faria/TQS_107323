package tqs.homework1.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tqs.homework1.Model.Ticket;
import tqs.homework1.Service.TicketService;

@RestController
public class TicketController {

    private final TicketService ticketService;


    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    
    @PostMapping("/createtickets")
    public ResponseEntity<String> createTicket(@RequestBody Ticket ticket) {
        ticketService.createTicket(ticket);
        System.out.println("Ticket created: " + ticket);
        return ResponseEntity.ok(Long.toString(ticket.getId())); 
    }


    @GetMapping("/alltickets")
    public ResponseEntity<Iterable<Ticket>> getAllTickets() {
        Iterable<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/ticketinfo/{ticketId}")
    public ResponseEntity<Ticket> getTicketInfo(@PathVariable Long ticketId) {
        Ticket ticket = ticketService.getTicketInfoById(ticketId);
        return ResponseEntity.ok(ticket);
    }


    @PostMapping("/ticket/addtrip")
    public ResponseEntity<String> addTripToTicket(@RequestBody Map<String, String> request) {
        try {
            String ticketId = request.get("ticketId");
            String tripId = request.get("tripId");

            long ticketIdLong = Long.parseLong(ticketId); 
            long tripIdLong = Long.parseLong(tripId);

            ticketService.addTripToTicket(ticketIdLong, tripIdLong);
            return ResponseEntity.ok("Trip added to ticket successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add trip to ticket.");
        }
    }

}

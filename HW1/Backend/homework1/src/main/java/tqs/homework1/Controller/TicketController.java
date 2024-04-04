package tqs.homework1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
}

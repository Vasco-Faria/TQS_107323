package tqs.homework1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.homework1.Model.Ticket;
import tqs.homework1.Repository.TicketRepository;

@Service
public class TicketService {

        private TicketRepository ticketRepository;

      @Autowired
      public TicketService(TicketRepository ticketRepository) {
          this.ticketRepository = ticketRepository;
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

    
}

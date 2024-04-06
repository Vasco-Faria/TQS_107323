package tqs.homework1.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.homework1.Model.Ticket;
import tqs.homework1.Model.Trip;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAll();
    Ticket findById(long id);
    boolean existsByTripAndSeat(Trip trip, String seat);
}

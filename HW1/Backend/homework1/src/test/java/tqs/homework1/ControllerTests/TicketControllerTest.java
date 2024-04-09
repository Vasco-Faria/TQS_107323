package tqs.homework1.ControllerTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import tqs.homework1.Controller.TicketController;
import tqs.homework1.Model.Ticket;
import tqs.homework1.Requests.PurchaseRequest;
import tqs.homework1.Service.TicketService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@DataJpaTest
public class TicketControllerTest {



    @Test
    public void testGetAllTickets() throws ParseException {
       
        TicketService ticketService = mock(TicketService.class);

        
        TicketController ticketController = new TicketController(ticketService);

        
        Ticket ticket1 = new Ticket();
        ticket1.setId(1L);
        ticket1.setOrigin("Origem1");
        ticket1.setDestiny("Destino1");
    
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2024-04-09");
        ticket1.setDate(date);


        Ticket ticket2 = new Ticket();
        ticket2.setId(2L);
        ticket2.setOrigin("Origem2");
        ticket2.setDestiny("Destino2");
        ticket2.setDate(date);


        
        when(ticketService.getAllTickets()).thenReturn(Arrays.asList(ticket1, ticket2));

       
        ResponseEntity<Iterable<Ticket>> responseEntity = ticketController.getAllTickets();

       
        verify(ticketService, times(1)).getAllTickets();

        
        assert responseEntity.getStatusCode() == HttpStatus.OK;

       
        Iterable<Ticket> tickets = responseEntity.getBody();
        assert tickets != null;
        assert tickets.iterator().next().getId() == 1L;
    }

    @Test
    public void testGetTicketInfo() throws ParseException {
       
        TicketService ticketService = mock(TicketService.class);

      
        TicketController ticketController = new TicketController(ticketService);

        
        Ticket sampleTicket = new Ticket();
        sampleTicket.setId(1L);
        sampleTicket.setOrigin("Aveiro");
        sampleTicket.setDestiny("Porto");
       
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2024-04-09");
        sampleTicket.setDate(date);


        
        when(ticketService.getTicketInfoById(1L)).thenReturn(sampleTicket);

        
        ResponseEntity<Ticket> responseEntity = ticketController.getTicketInfo(1L);

        
        verify(ticketService, times(1)).getTicketInfoById(1L);

        
        assert responseEntity.getStatusCode() == HttpStatus.OK;

        
        Ticket ticket = responseEntity.getBody();
        assert ticket != null;
        assert ticket.getId() == 1L;
    }


    @Test
    public void testAddTripToTicket_Success() {
       
        TicketService ticketService = mock(TicketService.class);

        
        TicketController ticketController = new TicketController(ticketService);

       
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("ticketId", "1");
        requestBody.put("tripId", "2");

        
        doNothing().when(ticketService).addTripToTicket(1L, 2L);

       
        ResponseEntity<String> responseEntity = ticketController.addTripToTicket(requestBody);

        
        verify(ticketService, times(1)).addTripToTicket(1L, 2L);

       
        assert responseEntity.getStatusCode() == HttpStatus.OK;

       
        assert responseEntity.getBody().equals("Trip added to ticket successfully.");
    }

    @Test
    public void testAddTripToTicket_Failure() {
        
        TicketService ticketService = mock(TicketService.class);

       
        TicketController ticketController = new TicketController(ticketService);

        
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("ticketId", "1");
        requestBody.put("tripId", "2");

        
        doThrow(new RuntimeException()).when(ticketService).addTripToTicket(1L, 2L);

        
        ResponseEntity<String> responseEntity = ticketController.addTripToTicket(requestBody);

       
        verify(ticketService, times(1)).addTripToTicket(1L, 2L);

       
        assert responseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR;

      
        assert responseEntity.getBody().equals("Failed to add trip to ticket.");
    }


    @Test
    public void testPurchaseTicket_Success() {
    
        TicketService ticketService = mock(TicketService.class);

     
        TicketController ticketController = new TicketController(ticketService);

       
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        purchaseRequest.setTicketId(123L);
    purchaseRequest.setName("John Doe");
    purchaseRequest.setAddress("123 Main St");
    purchaseRequest.setCity("Anytown");
    purchaseRequest.setState("CA");
    purchaseRequest.setZipCode("12345");
    purchaseRequest.setCardType("Visa");
    purchaseRequest.setCreditCardNumber("1234567890123456");
    purchaseRequest.setMonth("01");
    purchaseRequest.setYear("2025");
    purchaseRequest.setNameOnCard("John Doe");

        
        doNothing().when(ticketService).purchaseTicket(purchaseRequest);

        
        ResponseEntity<String> responseEntity = ticketController.purchaseTicket(purchaseRequest);

        
        verify(ticketService, times(1)).purchaseTicket(purchaseRequest);

        
        assert responseEntity.getStatusCode() == HttpStatus.OK;

       
        assert responseEntity.getBody().equals("Ticket purchased successfully.");
    }

    @Test
    public void testPurchaseTicket_Failure() {
        
        TicketService ticketService = mock(TicketService.class);

        
        TicketController ticketController = new TicketController(ticketService);

        
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        purchaseRequest.setTicketId(123L);
        purchaseRequest.setName("John Doe");
        purchaseRequest.setAddress("123 Main St");
        purchaseRequest.setCity("Anytown");
        purchaseRequest.setState("CA");
        purchaseRequest.setZipCode("12345");
        purchaseRequest.setCardType("Visa");
        purchaseRequest.setCreditCardNumber("1234567890123456");
        purchaseRequest.setMonth("01");
        purchaseRequest.setYear("2025");
        purchaseRequest.setNameOnCard("John Doe");

       
        doThrow(new RuntimeException()).when(ticketService).purchaseTicket(purchaseRequest);

        ResponseEntity<String> responseEntity = ticketController.purchaseTicket(purchaseRequest);

        
        verify(ticketService, times(1)).purchaseTicket(purchaseRequest);

        
        assert responseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR;

        assert responseEntity.getBody().equals("Failed to purchase ticket.");
    }
}

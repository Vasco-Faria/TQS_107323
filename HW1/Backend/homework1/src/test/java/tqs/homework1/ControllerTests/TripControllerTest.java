package tqs.homework1.ControllerTests;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tqs.homework1.Service.TripService;


@SpringBootTest
@AutoConfigureMockMvc
public class TripControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService tripService;

    @Test
    public void testGetAllTrips() throws Exception {
        mockMvc.perform(get("/alltrips"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    // @Test
    // public void testGetTripsByOriginAndDestination() throws Exception {
    //     String origin = "Lisboa";
    //     String destination = "Madrid";
    //     String date = "2024-04-10";

    //     mockMvc.perform(get("/trips")
    //             .param("origin", origin)
    //             .param("destination", destination)
    //             .param("date", date))
    //             .andExpect(status().isOk())
    //             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    // }

    @Test
    public void testGetTripsByOriginAndDestination_NotFound() throws Exception {
        String origin = "NonExistentOrigin";
        String destination = "NonExistentDestination";
        LocalDate date = LocalDate.of(2024, 4, 9);

        when(tripService.findTripsByOriginDestinationAndDate(origin, destination, java.sql.Date.valueOf(date)))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/trips")
                .param("origin", origin)
                .param("destination", destination)
                .param("date", date.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}


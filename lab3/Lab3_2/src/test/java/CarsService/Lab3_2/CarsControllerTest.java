package CarsService.Lab3_2;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import CarsService.Lab3_2.Controller.CarController;
import CarsService.Lab3_2.Model.Car;
import CarsService.Lab3_2.Service.CarService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;


@WebMvcTest(CarController.class)
public class CarsControllerTest {
    

    @Autowired
    private MockMvc mvc;


    @MockBean
    private CarService carService;

    @BeforeEach
    public void setUp() {
    }


    @Test
    public void testCreateCar() throws Exception {
        Car car = new Car("Renault", "Clio");

        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.post("/api/newcar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(car))
        );

        result.andExpect(status().isCreated());

        result.andExpect(jsonPath("$.maker").value("Renault"));
        result.andExpect(jsonPath("$.model").value("Clio"));

         verify(carService, times(1)).save(car);
    }


    @Test
    public void testgetAllCars() throws Exception {
        Car car1 = new Car("Renault", "Clio");
        Car car2 = new Car("Ford", "Fiesta");
        Car car3 = new Car ("Toyota", "Yaris");

        List<Car> cars = Arrays.asList(car1, car2, car3);

        when(carService.getAllCars()).thenReturn(cars);

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].maker").value("Renault"))
            .andExpect(jsonPath("$[1].maker").value("Ford"))
            .andExpect(jsonPath("$[2].maker").value("Toyota"));

            verify(carService, times(1)).getAllCars();
       
    }

    @Test 
    public void testGetCarById() throws Exception {
        Car car = new Car("Renault", "Clio");
        car.setCarId(1L);

        when(carService.getCarDetails(1L)).thenReturn(car);

        mvc.perform(get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.maker").value("Renault"))
            .andExpect(jsonPath("$.model").value("Clio"));

        verify(carService, times(1)).getCarDetails(1L);
    }

    @Test
    public void testGetCarByIdNotFound() throws Exception {
        when(carService.getCarDetails(1L)).thenReturn(null);

        mvc.perform(get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

        verify(carService, times(1)).getCarDetails(1L);
    }
    


}

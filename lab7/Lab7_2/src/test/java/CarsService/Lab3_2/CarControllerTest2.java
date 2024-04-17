package test.java.CarsService.Lab3_2;

import static org.mockito.ArgumentMatchers.any;
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


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@WebMvcTest(CarController.class)
public class CarControllerTest2 {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    public void testCreateCar() {
        Car car = new Car();
        
        given()
            .mockMvc(mockMvc)
            .contentType("application/json")
            .body(car)
        .when()
            .post("/api/newcar")
        .then()
            .statusCode(HttpStatus.CREATED.value()); 
    }

    @Test
    public void testGetAllCars() {
       

        given()
            .mockMvc(mockMvc)
        .when()
            .get("/api/cars")
        .then()
            .statusCode(HttpStatus.OK.value()) 
            .body("$", hasSize(greaterThan(0))); 
    }

    @Test
    public void testGetCarById() {
        

        given()
            .mockMvc(mockMvc)
        .when()
            .get("/api/cars/{id}", 1)
        .then()
            .statusCode(HttpStatus.OK.value());
            
    }
}

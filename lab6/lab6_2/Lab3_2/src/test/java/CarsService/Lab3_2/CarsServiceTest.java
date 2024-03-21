package CarsService.Lab3_2;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import CarsService.Lab3_2.Model.Car;
import CarsService.Lab3_2.Repository.CarRepository;
import CarsService.Lab3_2.Service.CarService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarsServiceTest {
    
    @Mock (lenient= true)
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;


    @BeforeEach
    public void setUp(){

        Car car = new Car("Renault","Clio");
        car.setCarId(1L);

        Car car2 = new Car("Ford", "Fiesta");
        Car car3 = new Car ("Toyota", "Yaris");


        when(carRepository.findByCarId(1L)).thenReturn(car);
        when(carRepository.findByCarId(22L)).thenReturn(null);
        
        List<Car> cars = Arrays.asList(car, car2, car3);
        when(carRepository.findAll()).thenReturn(cars);

    }


    @Test
    public void testGetCarDetailsTrue(){

        Car carToBeFound = carService.getCarDetails(1L);

        assertThat(carToBeFound.getMaker()).isEqualTo("Renault");

    }

    @Test
    public void testGetCarDetailsFalse(){
            
            Car carToBeFound = carService.getCarDetails(2L);
    
            assertThat(carToBeFound).isNull();
    }


    @Test
    public void testGetAllCars(){
            
            List<Car> cars = carService.getAllCars();
    
            assertThat(cars).hasSize(3).extracting(Car::getMaker).contains("Renault", "Ford", "Toyota");

            verify(carRepository).findAll();
    }

}

package CarsService.Lab3_2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import CarsService.Lab3_2.Model.Car;
import CarsService.Lab3_2.Repository.CarRepository;

@DataJpaTest
public class CarRepositoryTest {
    

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CarRepository carRepository;


    @Test
    public void testFindCarById(){
        Car car = new Car("Renault","Clio");

        testEntityManager.persistAndFlush(car);

        Car foundCar = carRepository.findByCarId(car.getCarId());

        assertThat(foundCar).isNotNull();
        assertThat(foundCar.getCarId()).isEqualTo(car.getCarId());

        
    }

    @Test
    public void testFindCarByIdNull(){
        
        Car foundCar = carRepository.findByCarId(2L);

        assertThat(foundCar).isNull();
    }


    @Test
    public void testFindAllCars(){
        Car car1 = new Car("Renault","Clio");
        Car car2 = new Car("Ford", "Fiesta");
        Car car3 = new Car ("Toyota", "Yaris");


        testEntityManager.persistAndFlush(car1);
        testEntityManager.persistAndFlush(car2);
        testEntityManager.persistAndFlush(car3);

        List<Car> cars = carRepository.findAll();


        assertThat(cars).isNotNull();
        assertThat(cars).hasSize(3).extracting(Car::getMaker).containsOnly(car1.getMaker(), car2.getMaker(), car3.getMaker());
    }
}

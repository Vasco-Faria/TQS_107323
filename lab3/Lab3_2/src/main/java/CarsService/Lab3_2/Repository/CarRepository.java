package CarsService.Lab3_2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import CarsService.Lab3_2.Model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
    public Car findByCarId(Long carId);
    public List<Car> findAll();
}
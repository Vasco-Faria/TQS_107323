package tqs.homework1.Model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;
    private String destination;
    private String company;
    private String departureTime;
    private String arrivalTime;
    private double price;
    @Temporal(TemporalType.DATE)
    private Date tripDate;
    private long availableseats;


    public Trip() {
        
    }

    public Trip(String origin, String destination, String company, String departureTime, String arrivalTime, double price) {
        this.origin = origin;
        this.destination = destination;
        this.company = company;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTripDate(){
        return tripDate;
    }

    public void setTripDate(Date tripDate){
        this.tripDate=tripDate;
    }

    public long getAvailableSeats() {
        return availableseats;
    }

    public void setAvailableSeats(long availableSeats) {
        this.availableseats = availableSeats;
    }
}
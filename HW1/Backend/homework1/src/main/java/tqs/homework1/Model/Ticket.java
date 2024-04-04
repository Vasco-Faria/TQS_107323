package tqs.homework1.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;


import java.util.Date;

import org.hibernate.boot.spi.AccessType;


@Entity
@Table(name = "bilhete")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String origin;
    private String destiny;
    private Date date;
    private Integer trip;
    private String company;
    private String departs;
    private String arrives;
    private String price;
    private String name;
    private String adress;
    private String city;
    private String state;
    private String zipCode;
    private String cardType;
    private Integer creditnumber;
    private Integer month;
    private Integer Year;
    private String cardName;

    public Ticket() {
    }

    public Ticket(String origin, String destiny, Date date, Integer trip, String company, String departs, String arrives, String price, String name, String adress, String city, String state, String zipCode, String cardType, Integer creditnumber, Integer month, Integer Year, String cardName) {
        this.origin = origin;
        this.destiny = destiny;
        this.date = date;
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

    public void setOrigin(String from) {
        this.origin = from;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String to) {
        this.destiny = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Integer getTrip() {
        return trip;
    }

    public void setTrip(Integer trip) {
        this.trip = trip;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDeparts() {
        return departs;
    }

    public void setDeparts(String departs) {
        this.departs = departs;
    }

    public String getArrives() {
        return arrives;
    }

    public void setArrives(String arrives) {
        this.arrives = arrives;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getCreditnumber() {
        return creditnumber;
    }

    public void setCreditnumber(Integer creditnumber) {
        this.creditnumber = creditnumber;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
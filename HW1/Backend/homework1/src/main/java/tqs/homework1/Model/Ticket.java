package tqs.homework1.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;




@Entity
@Table(name = "bilhete")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String origin;
    private String destiny;

    @Temporal(TemporalType.DATE)
    private Date date;
    
    @ManyToOne
    @JoinColumn(name = "trip_id") 
    private Trip trip;

    private String seat;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String cardType;
    private String creditnumber;
    private String  month;
    private String  Year;
    private String cardName;

    public Ticket() {
    }

    public Ticket(String origin, String destiny, Date date) {
        this.origin=origin;
        this.destiny=destiny;
        this.date=date;
    }

    public Ticket(String origin, String destiny, Date date,Trip trip, String name, String adress, String city, String state, String zipCode, String cardType, String  creditnumber, String  month, String  Year, String cardName) {
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

    public Trip getTrip(){
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
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

    public String  getCreditnumber() {
        return creditnumber;
    }

    public void setCreditnumber(String  creditnumber) {
        this.creditnumber = creditnumber;
    }

    public String  getMonth() {
        return month;
    }

    public void setMonth(String  month) {
        this.month = month;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String  year) {
        Year = year;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
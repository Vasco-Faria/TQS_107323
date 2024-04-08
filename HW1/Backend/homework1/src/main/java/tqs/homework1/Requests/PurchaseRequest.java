package tqs.homework1.Requests;


public class PurchaseRequest {
    private Long ticketId;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String cardType;
    private String creditCardNumber;
    private String  month;
    private String   year;
    private String nameOnCard;


    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
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

    public void setAddress(String address) {
        this.address = address;
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

    public String   getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String   creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String  getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String  getYear() {
        return year;
    }

    public void setYear(String  year) {
        this.year = year;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }
}

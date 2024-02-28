package geocoding;

import java.util.Objects;


public class Address 
{

    private String houseNumber;
    private String city;
    private String road;
    public String zipCode;

    public Address(String houseNumber, String city, String road, String zipCode) {
        this.houseNumber = houseNumber;
        this.city = city;
        this.road = road;
        this.zipCode = zipCode;
    }

    public Address(){

    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    public String getRoad() {
        return road;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(houseNumber, address.houseNumber) &&
                Objects.equals(city, address.city) &&
                Objects.equals(road, address.road);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseNumber, city, road);
    }

    @Override
    public String toString() {
        return "Address{" +
                "houseNumber='" + houseNumber + '\'' +
                ", city='" + city + '\'' +
                ", road='" + road + '\'' +
                '}';
    }
    



}

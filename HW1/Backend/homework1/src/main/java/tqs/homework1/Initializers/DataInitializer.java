package tqs.homework1.Initializers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tqs.homework1.Model.Trip;
import tqs.homework1.Repository.TripRepository;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TripRepository tripRepository;

    @Override
    public void run(String... args) throws Exception {

        if (tripRepository.count() == 0) {
            java.util.Date currentDate = new java.util.Date();
    
            for (int i = 0; i < 5; i++) {
                java.util.Date tripDate = new java.util.Date(currentDate.getTime() + i * 24 * 60 * 60 * 1000);
                generateTripsForDate(tripDate);
            }
        }
    }

    private void generateTripsForDate(java.util.Date tripDate) {
        String[] cities = {"Aveiro", "Barcelona", "Faro", "Lisboa", "Madrid", "Porto", "Sevilha", "Valencia", "Viana"};

        int tripNumber = 1;
        for (String origin : cities) {
            for (String destination : cities) {
                if (!origin.equals(destination)) {
                    for (int i = 0; i < 5; i++) {
                        String company;
                        String departureTime;
                        String arrivalTime;
                        double price;

                        switch (tripNumber % 5) {
                            case 1:
                                company = "BusIberia";
                                departureTime = "8:45 AM";
                                arrivalTime = "11:15 AM";
                                price = 10.0;
                                break;
                            case 2:
                                company = "FlixBus";
                                departureTime = "4:22 PM";
                                arrivalTime = "5:13 PM";
                                price = 20.0;
                                break;
                            case 3:
                                company = "Rede Expressos";
                                departureTime = "1:43 AM";
                                arrivalTime = "4:43 AM";
                                price = 30.0;
                                break;
                            case 4:
                                company = "BusIberia";
                                departureTime = "7:11 PM";
                                arrivalTime = "9:00 PM";
                                price = 40.0;
                                break;
                            case 0:
                                company = "Ovnitur";
                                departureTime = "1:57 PM";
                                arrivalTime = "3:12 PM";
                                price = 50.0;
                                break;
                            default:
                                company = "Unknown";
                                departureTime = "N/A";
                                arrivalTime = "N/A";
                                price = 0.0;
                        }

                        // Formatando a data para o formato desejado (dia/mês/ano)
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = dateFormat.format(tripDate);

                        Date sqlDate = new Date(tripDate.getTime());
                        int availableSeats = generateRandomSeats();

                        Trip trip = new Trip(origin, destination, company, departureTime, arrivalTime, price);
                        trip.setAvailableSeats(availableSeats);
                        trip.setTripDate(sqlDate);
                        tripRepository.save(trip);
                        tripNumber++;
                    }
                }
            }
        }
    }

    private int generateRandomSeats() {
        Random random = new Random();
        return random.nextInt(11);
    }
}

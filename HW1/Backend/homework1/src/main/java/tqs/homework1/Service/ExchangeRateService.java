package tqs.homework1.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeRateService {

    public Map<String, Double> getExchangeRates() {
        Map<String, Double> exchangeRates = new HashMap<>();

        String url = "https://v6.exchangerate-api.com/v6/5355f9240389a8a8968ad296/latest/USD";

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode ratesNode = rootNode.path("conversion_rates");

            exchangeRates.put("USD",1.00);
            exchangeRates.put("EUR", ratesNode.get("EUR").asDouble());
            exchangeRates.put("GBP", ratesNode.get("GBP").asDouble());
            exchangeRates.put("JPY", ratesNode.get("JPY").asDouble());

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exchangeRates;
    }
}

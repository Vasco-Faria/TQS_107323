package tqs.homework1.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateService {

    private final Map<String, Double> exchangeRatesCache = new HashMap<>();
    private long lastUpdateTime = 0;
    private static final long CACHE_DURATION = 30 * 60 * 1000; 

    public Map<String, Double> getExchangeRates() {
        if (System.currentTimeMillis() - lastUpdateTime < CACHE_DURATION) {
            return exchangeRatesCache; 
        }

        String url = "https://v6.exchangerate-api.com/v6/5355f9240389a8a8968ad296/latest/USD";
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode ratesNode = rootNode.path("conversion_rates");

            Map<String, Double> newRates = new HashMap<>();
            newRates.put("USD", 1.00);
            newRates.put("EUR", ratesNode.get("EUR").asDouble());
            newRates.put("GBP", ratesNode.get("GBP").asDouble());
            newRates.put("JPY", ratesNode.get("JPY").asDouble());

            exchangeRatesCache.clear();
            exchangeRatesCache.putAll(newRates);
            lastUpdateTime = System.currentTimeMillis();

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exchangeRatesCache;
    }
}

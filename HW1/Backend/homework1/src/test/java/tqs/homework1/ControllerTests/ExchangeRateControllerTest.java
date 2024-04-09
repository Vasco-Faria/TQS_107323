package tqs.homework1.ControllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tqs.homework1.Controller.ExchangeRateController;
import tqs.homework1.Service.ExchangeRateService;

public class ExchangeRateControllerTest {

    private ExchangeRateController exchangeRateController;
    private ExchangeRateService exchangeRateService;

    @BeforeEach
    public void setUp() {
        exchangeRateService = mock(ExchangeRateService.class);
        exchangeRateController = new ExchangeRateController();
        exchangeRateController.setExchangeRateService(exchangeRateService);
    }

    @Test
    public void testGetPrices() {
        Map<String, Double> mockRates = new HashMap<>();
        mockRates.put("USD", 1.0);
        mockRates.put("EUR", 0.85);
        mockRates.put("GBP", 0.73);
        mockRates.put("JPY", 109.81);

        when(exchangeRateService.getExchangeRates()).thenReturn(mockRates);

        ResponseEntity<Map<String, Double>> responseEntity = exchangeRateController.getPrices();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockRates, responseEntity.getBody());
    }
}
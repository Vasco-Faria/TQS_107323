package tqs.homework1.ServiceTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import tqs.homework1.Service.ExchangeRateService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Map;

public class ExchangeRateServiceTest {

    private ExchangeRateService exchangeRateService;

    @BeforeEach
    public void setUp() {
        exchangeRateService = new ExchangeRateService();
    }

    @Test
    @DisplayName("Test Rates")
    public void testGetExchangeRates_ReturnsAtLeastFourRates() {
        Map<String, Double> exchangeRates = exchangeRateService.getExchangeRates();
        assertTrue(exchangeRates.size() >= 4, "Expecting at least four exchange rates");
    }

    @Test
    @DisplayName("Update second call ")    
    public void testGetExchangeRates_SecondCall_ReturnsSameValues() {
        Map<String, Double> firstCallRates = exchangeRateService.getExchangeRates();
        Map<String, Double> secondCallRates = exchangeRateService.getExchangeRates();

        assertEquals(firstCallRates.size(), secondCallRates.size(), "Number of exchange rates should be same");

        for (Map.Entry<String, Double> entry : firstCallRates.entrySet()) {
            assertTrue(secondCallRates.containsKey(entry.getKey()), "Second call should contain all keys from the first call");
            assertEquals(entry.getValue(), secondCallRates.get(entry.getKey()), "Values should be equal for the same key");
        }
    }

    @Test
    @DisplayName("Second call cache update from API")    
    public void testCacheExpiration() {
        
        ExchangeRateService exchangeRateService = new ExchangeRateService();

        
        ExchangeRateService mockService = Mockito.spy(exchangeRateService);
        Mockito.when(mockService.updateLastUpdateTime()).thenReturn(System.currentTimeMillis() + 31 * 60 * 1000);

        
        Map<String, Double> initialRates = mockService.getExchangeRates();

        
        Map<String, Double> updatedRates = mockService.getExchangeRates();

        
        assertEquals(initialRates.size(), updatedRates.size(), "O número de taxas de câmbio deve ser o mesmo após um longo período de tempo");
    }
}


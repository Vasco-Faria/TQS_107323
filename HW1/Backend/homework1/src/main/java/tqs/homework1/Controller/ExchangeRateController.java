package tqs.homework1.Controller;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.homework1.Service.ExchangeRateService;

@RestController
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    public void setExchangeRateService(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }


    @GetMapping("/prices")
    public ResponseEntity<Map<String, Double>> getPrices() {
        Map<String, Double> exchangeRates = exchangeRateService.getExchangeRates();
        return ResponseEntity.ok(exchangeRates);
    }
}

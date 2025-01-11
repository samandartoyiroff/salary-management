package result.agency.result_agency_intern.util;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final RestTemplate restTemplate;

    @Value("${currency.api.url}")
    private String apiUrl;

    @Value("${currency.api.key}")
    private String apiKey;


    public Double toTransferUZS(@NotNull(message = "currency cannot be null") String currency, @NotNull(message = "Amount cannot be null") Double amount) {

        String url = apiUrl + "?access_key=" + apiKey;
        Map response = restTemplate.getForObject(url, Map.class);
        Map<String, Double> rates = (Map<String, Double>) response.get("conversion_rates");
        if (rates.get(currency) == null) return amount;
        double v = ((Number) rates.get(currency)).doubleValue();
        double uzsPerCurrentCurrency = ((Number) rates.get("UZS")).doubleValue() / v;
        amount*=uzsPerCurrentCurrency;
        DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Format with 2 decimal places
        return Double.parseDouble(decimalFormat.format(amount));
    }
}

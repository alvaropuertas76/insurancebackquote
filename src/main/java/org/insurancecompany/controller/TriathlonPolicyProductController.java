package org.insurancecompany.controller;

import org.springframework.web.client.RestTemplate;
import org.insurancecompany.api.TriatlhonPolicyProductApi;
import org.insurancecompany.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
public class TriathlonPolicyProductController implements TriatlhonPolicyProductApi {

    private final RestTemplate restTemplate;

    private static final String NEWLINE = "<BR>";

    private static final Double RAINY_RISK = 50.0d;

    private static final String CALL_INTEGRATION_WEATHER = "http://localhost:8081/forecastWeather?";

    private static final String CALL_MASTER_DATA_EVENT = "http://localhost:8080/event";

    private static final String CALL_MASTER_DATA_BIKE = "http://localhost:8080/bike";

    private static final String CALL_MASTER_DATA_WETSUIT = "http://localhost:8080/wetsuit";

    private static final String CALL_MASTER_DATA_HELMET = "http://localhost:8080/helmet";

    public TriathlonPolicyProductController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> getQuote(String eventId, String bikeId, String wetsuitId, String helmetId) {
        double totalMarketValue = 0d;
        double quote = 0d;

        JSONObject jsonObject = new JSONObject();
        String response = "Thanks for choosing Zurich Insurance. ";
        jsonObject.put("title", response);

        if (eventId != null) {
            String llamada_event = CALL_MASTER_DATA_EVENT + "?id=" + eventId;
            Event[] event = restTemplate.getForObject(llamada_event, Event[].class);
            Event eventSelected = event[0];
            response += " We hope to enjoy next " + eventSelected.getName() + " in " + eventSelected.getCity() + NEWLINE;
            jsonObject.put("event_name", eventSelected.getName());
            jsonObject.put("event_city", eventSelected.getCity());

            if (bikeId != null) {
                String llamada_bike = CALL_MASTER_DATA_BIKE + "?id=" + bikeId;
                Bike[] bike = restTemplate.getForObject(llamada_bike, Bike[].class);
                Bike bikeSelected = bike[0];
                totalMarketValue += bikeSelected.getMarketValue();

                double quotebike = bikeSelected.getMarketValue() * 0.05;
                jsonObject.put("currency", bikeSelected.getCurrency());
                String[] location = eventSelected.getLocation().split("&");
                String lat = location[0].split("=")[1];
                String lon = location[1].split("=")[1];
                String llamada_weather = CALL_INTEGRATION_WEATHER + "lat=" + lat + "&" + "lon=" + lon;

                Double maxProbability = restTemplate.getForObject(llamada_weather, Double.class);
                // We increase the quote of the bike because the
                // probability to rain is high
                // More probability ro rain = more probability to have an accident
                if (maxProbability > RAINY_RISK) {
                    quotebike = quotebike * 1.5d;
                    response += " Be carefull in ride segment because the probability to rain is high."  + NEWLINE;
                    jsonObject.put("probability_rain", maxProbability);
                }
                quote += quotebike;
            }

            if (wetsuitId != null) {
                String llamada_wetsuit = CALL_MASTER_DATA_WETSUIT + "?id=" + wetsuitId;
                Wetsuit[] wetsuit = restTemplate.getForObject(llamada_wetsuit, Wetsuit[].class);
                Wetsuit wetsuitSelected = wetsuit[0];
                totalMarketValue += wetsuitSelected.getMarketValue();
                double quotewetsuit = wetsuitSelected.getMarketValue() * 0.05;
                quote += quotewetsuit;
            }

            if (helmetId != null) {
                String llamada_helmet = CALL_MASTER_DATA_HELMET + "?id=" + helmetId;
                Helmet[] helmet = restTemplate.getForObject(llamada_helmet, Helmet[].class);
                Helmet helmetSelected = helmet[0];
                totalMarketValue += helmetSelected.getMarketValue();
                double quotehelmet = helmetSelected.getMarketValue() * 0.05;
                quote += quotehelmet;
            }

            BigDecimal valorRedondeado = new BigDecimal(quote).setScale(2, RoundingMode.HALF_UP);
            // Convertir de nuevo a double si es necesario
            quote = valorRedondeado.doubleValue();

            response += "the market value of all your gear to do the Ironman is " + totalMarketValue + NEWLINE;
            response += "THE QUOTE PRICE FOR ALL THE GEAR SELECTED IS : " + quote + " EUR.";

            jsonObject.put("total_market_value", totalMarketValue);
            jsonObject.put("quote", quote);

        }
        // return the information about the products inside the policy for
        // the event selected, with the cost of all of them and the quote
        // for the policy.
        return ResponseEntity.ok(jsonObject.toString());
        //return ResponseEntity.ok(response);
    }
}
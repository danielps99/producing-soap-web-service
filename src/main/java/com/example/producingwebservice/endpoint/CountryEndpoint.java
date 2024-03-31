package com.example.producingwebservice.endpoint;


import com.example.producingwebservice.gs_producing_web_service.Country;
import com.example.producingwebservice.gs_producing_web_service.Currency;
import com.example.producingwebservice.gs_producing_web_service.GetCountryRequest;
import com.example.producingwebservice.gs_producing_web_service.GetCountryResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Random;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

//    private CountryRepository countryRepository;
//
//    @Autowired
//    public CountryEndpoint(CountryRepository countryRepository) {
//        this.countryRepository = countryRepository;
//    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
//        response.setCountry(countryRepository.findCountry(request.getName()));
        Random random = new Random();
        Country country = new Country();
        country.setName(String.format("nome %s %s", request.getName(), generateRandomCharactersSequence(random)));
        country.setCapital("capital " + generateRandomCharactersSequence(random));
        country.setPopulation(random.nextInt());
        country.setCurrency(Currency.EUR);
        response.setCountry(country);

        return response;
    }

    private String generateRandomCharactersSequence(Random random ){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 10; i++) {
            sb.append(randomLetterChar(random));
        }
        return sb.toString();
    }

    private static String randomLetterChar(Random random) {
        int randomChar = random.nextInt(122 - 97) + 97;
        return String.valueOf((char) randomChar);
    }
}

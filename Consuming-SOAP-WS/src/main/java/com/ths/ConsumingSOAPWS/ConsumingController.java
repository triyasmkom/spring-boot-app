package com.ths.ConsumingSOAPWS;

import com.ths.ConsumingSOAPWS.wsdl.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumingController {

    @Autowired
    CountryClient countryClient = new CountryClient();

    @GetMapping("/cek-country/{country}")
    public GetCountryResponse getCountry(@PathVariable("country") String country){
        GetCountryResponse output = null;

        GetCountryResponse response = countryClient.getCountry(country);
        System.err.println(response.getCountry().getCurrency());
        output = response;
        return output;
    }
}

package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Country;
import ba.codecentric.base.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = "/countries")
    public List<Country> getCountries() {
        return countryService.getAllCountries();
    }
}

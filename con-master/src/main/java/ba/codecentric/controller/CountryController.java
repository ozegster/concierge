package ba.codecentric.controller;

import ba.codecentric.base.domain.Country;
import ba.codecentric.base.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public ResponseEntity<List<Country>> getCountries() {
        return new ResponseEntity<List<Country>>(countryService.getAllCountries(), HttpStatus.OK);
    }
}

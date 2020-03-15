package de.planerio.developertest.controller.country;

import de.planerio.developertest.exception.CountryNotFoundException;
import de.planerio.developertest.model.Country;
import de.planerio.developertest.model.CountryRequest;
import de.planerio.developertest.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController implements CountryResource {

    private static final Logger log = LoggerFactory.getLogger(CountryController.class);
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Iterable<Country> retrieveAllCountries() {
        return countryService.findAll().orElseThrow(() -> new CountryNotFoundException("There are no countries found"));
    }

    @Override
    public Country retrieveCountry(long countryId) {
        return countryService.find(countryId).orElseThrow(() -> new CountryNotFoundException("There is no country found"));
    }

    @Override
    public Country createCountry(CountryRequest countryRequest) {
        return countryService.save(countryRequest);
    }

    @Override
    public void deleteCountry(long countryId) {
        countryService.find(countryId).orElseThrow(() -> new CountryNotFoundException("There is no country found"));
        countryService.delete(countryId);
    }

    @Override
    public void updateCountry(Country country, long countryId) {
        countryService.find(countryId).orElseThrow(() -> new CountryNotFoundException("There is no country found"));
        countryService.update(country);
    }
}

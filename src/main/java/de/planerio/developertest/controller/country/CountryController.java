package de.planerio.developertest.controller.country;

import de.planerio.developertest.exception.CountryNotFoundException;
import de.planerio.developertest.exception.ResourceExistsException;
import de.planerio.developertest.model.Country;
import de.planerio.developertest.model.CountryCreate;
import de.planerio.developertest.model.CountryUpdate;
import de.planerio.developertest.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

import static de.planerio.developertest.exception.Constants.NO_COUNTRY_FOUND;
import static de.planerio.developertest.exception.Constants.NO_COUNTRIES_FOUND;
import static de.planerio.developertest.exception.Constants.COUNTRY_REGISTERED;

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
        return countryService.findAll().orElseThrow(() -> new CountryNotFoundException(NO_COUNTRIES_FOUND));
    }

    @Override
    public Country retrieveCountry(long countryId) {
        return countryService.find(countryId).orElseThrow(() -> new CountryNotFoundException(NO_COUNTRY_FOUND));
    }

    @Override
    public Country createCountry(@Valid CountryCreate countryRequest) {
        final Optional<Country> country = countryService.findCountryByNameAndLanguage(countryRequest.getName(), countryRequest.getLanguage());
        if(country.isPresent()){
            throw new ResourceExistsException(
                    String.format(COUNTRY_REGISTERED, countryRequest.getName(), countryRequest.getLanguage()));
        }
        return countryService.save(countryRequest);
    }

    @Override
    public void deleteCountry(long countryId) {
        countryService.find(countryId).orElseThrow(() -> new CountryNotFoundException(NO_COUNTRY_FOUND));
        countryService.delete(countryId);
    }

    @Override
    public void updateCountry(CountryUpdate countryUpdate, long countryId) {
        Country country = countryService.find(countryId).orElseThrow(() -> new CountryNotFoundException(NO_COUNTRY_FOUND));
        countryService.update(country, countryUpdate);
    }
}

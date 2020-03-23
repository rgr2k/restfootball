package de.planerio.developertest.controller.country;

import de.planerio.developertest.exception.ResourceExistsException;
import de.planerio.developertest.model.Country;
import de.planerio.developertest.model.CountryRequest;
import de.planerio.developertest.model.CountryResponse;
import de.planerio.developertest.model.CountryUpdateRequest;
import de.planerio.developertest.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public List<CountryResponse> getCountries() {
        return countryService.findAll();
    }

    @Override
    public CountryResponse getCountry(long countryId) {
        return countryService.find(countryId);
    }

    @Override
    public CountryResponse createCountry(@Valid CountryRequest countryRequest) {
        final Optional<Country> country = countryService.findCountryByNameAndLanguage(countryRequest.getName(), countryRequest.getLanguage());
        if(country.isPresent()){
            throw new ResourceExistsException(
                    String.format(COUNTRY_REGISTERED, countryRequest.getName(), countryRequest.getLanguage()));
        }
        return countryService.save(countryRequest);
    }

    @Override
    public void deleteCountry(long countryId) {
        countryService.delete(countryId);
    }

    @Override
    public void updateCountry(CountryUpdateRequest countryUpdate, long countryId) {
        countryService.update(countryUpdate, countryId);
    }
}

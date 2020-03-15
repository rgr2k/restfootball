package de.planerio.developertest.controller.country;

import de.planerio.developertest.model.Country;
import org.springframework.stereotype.Controller;

@Controller
public class CountryController implements CountryResource {

    @Override
    public Iterable<Country> getCountries() {
        return null;
    }

    @Override
    public void deleteCountry(long countryId) {

    }

    @Override
    public void updateCountry(Country updatedCountry, long countryId) {

    }
}

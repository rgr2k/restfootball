package de.planerio.developertest.transformer;

import de.planerio.developertest.model.Country;
import de.planerio.developertest.model.CountryRequest;
import de.planerio.developertest.model.CountryResponse;

public class CountryTransformer {

    public static CountryResponse toResponse(Country country){
        return new CountryResponse(country.getId(), country.getName(), country.getLanguage());
    }

    public static Country toEntity(CountryRequest countryRequest){
        return new Country(countryRequest.getName(), countryRequest.getName());
    }
}

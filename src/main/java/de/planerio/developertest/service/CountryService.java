package de.planerio.developertest.service;

import com.google.common.base.Strings;
import de.planerio.developertest.exception.CountryNotFoundException;
import de.planerio.developertest.model.Country;
import de.planerio.developertest.model.CountryRequest;
import de.planerio.developertest.model.CountryResponse;
import de.planerio.developertest.model.CountryUpdateRequest;
import de.planerio.developertest.repository.CountryRepository;
import de.planerio.developertest.transformer.CountryTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

import static de.planerio.developertest.exception.Constants.NO_COUNTRIES_FOUND;
import static de.planerio.developertest.exception.Constants.NO_COUNTRY_FOUND;
import static java.util.stream.StreamSupport.stream;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryResponse save(CountryRequest countryRequest){
        final Country country = CountryTransformer.toEntity(countryRequest);
        return CountryTransformer.toResponse(countryRepository.save(country));
    }

    public CountryResponse find(long countryId){
        final Country country =
                countryRepository.findById(countryId)
                        .orElseThrow(() -> new CountryNotFoundException(NO_COUNTRY_FOUND));
        return CountryTransformer.toResponse(country);
    }

    public List<CountryResponse> findAll(){
        List<CountryResponse> countries = stream(countryRepository.findAll().spliterator(), false)
                .map(CountryTransformer::toResponse)
                .collect(Collectors.toList());

        if(countries.isEmpty()){
            throw new CountryNotFoundException(NO_COUNTRIES_FOUND);
        }
        return countries;
    }

    public void delete(long countryId){
        countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(NO_COUNTRY_FOUND));
        countryRepository.deleteById(countryId);
    }

    public void update(CountryUpdateRequest countryUpdateRequest, long countryId){
        Country country =
                countryRepository.findById(countryId)
                        .orElseThrow(() -> new CountryNotFoundException(NO_COUNTRY_FOUND));
        validate(country, countryUpdateRequest);
        countryRepository.save(country);
    }

    public Optional<Country> findCountryByNameAndLanguage(String name, String language){
        return countryRepository.findCountryByNameAndLanguage(name,language);
    }

    private void validate(Country country, CountryUpdateRequest countryUpdate) {
        if(!Strings.isNullOrEmpty(countryUpdate.getName())){
            country.setName(countryUpdate.getName());
        }
        if(!Strings.isNullOrEmpty(countryUpdate.getLanguage())){
            country.setLanguage(countryUpdate.getLanguage());
        }
    }
}

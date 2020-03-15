package de.planerio.developertest.service;

import de.planerio.developertest.model.Country;
import de.planerio.developertest.model.CountryRequest;
import de.planerio.developertest.repository.CountryRepository;
import de.planerio.developertest.util.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country save(CountryRequest countryRequest){
        final Country country = (Country) Transformation.of(countryRequest, CountryRequest.class, Country.class);
        return countryRepository.save(country);
    }

    public Optional<Country> find(long countryId){
        return countryRepository.findById(countryId);
    }

    public Optional<Iterable<Country>> findAll(){
        final Iterable<Country> countries = countryRepository.findAll();
        return countries.iterator().hasNext() ? Optional.of(countries) : Optional.empty();
    }

    public void delete(long countryId){
        countryRepository.deleteById(countryId);
    }

    public void update(Country country){
        countryRepository.save(country);
    }
}

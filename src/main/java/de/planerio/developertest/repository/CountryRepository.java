package de.planerio.developertest.repository;

import de.planerio.developertest.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}

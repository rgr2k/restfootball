package de.planerio.developertest.repository;

import de.planerio.developertest.model.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    Country findCountryByNameAndLanguage(String name, String language);

    @Query("SELECT c from Country c where c.name = ?1")
    Country findCountryByName(@Param("name") String name);
}

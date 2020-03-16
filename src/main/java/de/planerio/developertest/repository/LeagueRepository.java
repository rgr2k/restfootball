package de.planerio.developertest.repository;

import de.planerio.developertest.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LeagueRepository extends JpaRepository<League, Long> {

    @Query("SELECT l FROM League l where l.country.name = ?1")
    Optional<League> findLeagueByCountryName( @Param("name") String name);
}

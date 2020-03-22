package de.planerio.developertest.repository;

import de.planerio.developertest.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    boolean existsByShirtNumber(int shirtNumber);
}

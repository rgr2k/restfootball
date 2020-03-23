package de.planerio.developertest.repository;

import de.planerio.developertest.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerRepositoryCustom {

    boolean existsByShirtNumber(int shirtNumber);
}

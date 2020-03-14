package de.planerio.developertest.repository;

import de.planerio.developertest.model.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}

package de.planerio.developertest.repository;

import de.planerio.developertest.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
}

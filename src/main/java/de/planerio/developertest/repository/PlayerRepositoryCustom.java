package de.planerio.developertest.repository;

import de.planerio.developertest.model.OrderBy;
import de.planerio.developertest.model.Player;
import de.planerio.developertest.model.PlayerPosition;

import java.util.List;

public interface PlayerRepositoryCustom {

    List<Player> findPlayerByPositionsInSort(List<PlayerPosition> positions, String sortBy, OrderBy orderBy);
}

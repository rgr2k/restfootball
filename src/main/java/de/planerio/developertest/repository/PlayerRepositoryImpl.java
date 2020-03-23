package de.planerio.developertest.repository;

import de.planerio.developertest.model.OrderBy;
import de.planerio.developertest.model.Player;
import de.planerio.developertest.model.PlayerPosition;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerRepositoryImpl implements PlayerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List findPlayerByPositionsInSort(List<PlayerPosition> positions, String sortBy, OrderBy orderBy) {

        String sql = String.format("SELECT * FROM player p WHERE p.position IN (:positions) order by p.%s %s", sortBy, orderBy);
        //I had to map the list of enum values to list of string. Somehow, H2 database doesn't accept a list of enum values.
        List<String> positionsList = positions.stream().map(Enum::name).collect(Collectors.toList());
        Query query = entityManager.createNativeQuery(sql, Player.class);
        query.setParameter("positions", positionsList);

        return query.getResultList();
    }
}

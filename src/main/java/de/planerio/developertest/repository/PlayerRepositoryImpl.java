package de.planerio.developertest.repository;

import de.planerio.developertest.model.PlayerPosition;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class PlayerRepositoryImpl implements PlayerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List findPlayerByPositionsInSort(List<PlayerPosition> positions, String sortBy, String orderBy) {

        String sql = String.format("SELECT * from player p where p.position in (:positions) order by p.%s %s", sortBy, orderBy);

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("positions", positions);

        return query.getResultList();
    }
}

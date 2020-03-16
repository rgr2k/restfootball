package de.planerio.developertest.repository;

import de.planerio.developertest.model.SimpleObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimpleObjectRepository extends CrudRepository<SimpleObject, Long> {
    SimpleObject findById(long id);
    List<SimpleObject> findByName(String name);
    List<SimpleObject> findAll();
}

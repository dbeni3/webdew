package hu.unideb.webdev.dao;


import hu.unideb.webdev.dao.entity.StoreEntity;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<StoreEntity, Integer> {
}

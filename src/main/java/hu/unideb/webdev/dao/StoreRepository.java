package hu.unideb.webdev.dao;


import hu.unideb.webdev.dao.entity.AddressEntity;
import hu.unideb.webdev.dao.entity.CountryEntity;
import hu.unideb.webdev.dao.entity.StoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface StoreRepository extends CrudRepository<StoreEntity, Integer> {
    Collection<StoreEntity> findById(int id);
    Collection<StoreEntity> findByAdress_Id(int id);
}

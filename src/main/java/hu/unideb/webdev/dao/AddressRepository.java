package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.AddressEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {
    Collection<AddressEntity> findByAddress(String name);
}

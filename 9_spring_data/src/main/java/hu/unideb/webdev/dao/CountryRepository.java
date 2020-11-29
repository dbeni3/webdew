package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<CountryEntity, Integer> {

    @Override
    Optional<CountryEntity> findById(Integer integer);

    CountryEntity findByName(String name);
}

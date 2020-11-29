package hu.unideb.webdev.dao;


import hu.unideb.webdev.dao.entity.StaffEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<StaffEntity, Integer>  {
}

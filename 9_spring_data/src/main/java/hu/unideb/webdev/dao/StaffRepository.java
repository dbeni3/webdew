package hu.unideb.webdev.dao;


import hu.unideb.webdev.dao.entity.StaffEntity;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<StaffEntity, Integer>  {
}

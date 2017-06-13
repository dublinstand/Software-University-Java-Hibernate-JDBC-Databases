package com.mappingobjects.repository;

import com.mappingobjects.domain.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

    List<Employee> findByFirstName (String name);

    @Query(value = "SELECT e FROM Employee AS e " +
            "WHERE e.birthday < 01-01-1980 " +
            "ORDER BY e.salary DESC")
    List<Employee> findAllBefore1980();
}

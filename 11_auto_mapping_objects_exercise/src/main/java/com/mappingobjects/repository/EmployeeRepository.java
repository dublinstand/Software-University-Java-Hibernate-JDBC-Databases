package com.mappingobjects.repository;

import com.mappingobjects.domain.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

    List<Employee> findByFirstName (String name);
}

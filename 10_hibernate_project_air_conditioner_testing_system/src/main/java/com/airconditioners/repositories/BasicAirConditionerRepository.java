package com.airconditioners.repositories;

import com.airconditioners.domain.entities.airConditioners.BasicAirConditioner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicAirConditionerRepository extends CrudRepository<BasicAirConditioner, Long>{

    //we want to verify if an air conditioner with model and manufacturer exists
    int countByModelAndManufacturer(String model, String manufacturer);

    //for the Report we need to get the air conditioner by model and manufacture
    BasicAirConditioner findOneByModelAndManufacturer(String model, String manufacturer);

    @Query(value = "SELECT SUM(CASE WHEN r.id IS NOT NULL THEN 1.00 ELSE 0.00 END)/COUNT(a)*100 " +
            "FROM BasicAirConditioner AS a " +
            "LEFT JOIN a.report AS r")
    double findTestedAirConditioners();
}

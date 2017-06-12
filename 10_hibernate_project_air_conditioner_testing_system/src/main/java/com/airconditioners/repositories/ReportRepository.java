package com.airconditioners.repositories;

import com.airconditioners.domain.entities.reports.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long>{

    //verify if there is already a report for this model and manufacturer
    //because the model and the manufacturer are in basicAirConditioner table we need to use JPQL, it will return countByModelAndManufacturer of all rows
    //!!!important all comments should have at least one line from the query

    @Query(value = "SELECT COUNT(r) FROM Report as r " +
            "JOIN r.basicAirConditioner AS a " +
            "WHERE a.model = :model " +
            "AND a.manufacturer = :manufacturer")
    int countByModelAndManufacturer(@Param(value = "model") String model, @Param(value = "manufacturer") String manufacturer);

    @Query(value = "SELECT r FROM Report as r " +
            "JOIN r.basicAirConditioner AS a " +
            "WHERE a.model = :model " +
            "AND a.manufacturer = :manufacturer")
    Report findOneByModelAndManufacturer(@Param(value = "model") String model, @Param(value = "manufacturer") String manufacturer);

    @Query(value = "SELECT r FROM Report as r " +
            "JOIN r.basicAirConditioner AS a " +
            "WHERE a.manufacturer = :manufacturer")
    List<Report> findAllByManufacturer(@Param(value = "manufacturer") String manufacturer);
}

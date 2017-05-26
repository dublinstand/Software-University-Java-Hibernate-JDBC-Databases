package com.neckandelbows.dao;


import com.neckandelbows.domain.batches.ProductionBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductionBatchDao extends JpaRepository<ProductionBatch, Long> {

    //5.Select Batches by Date
    //Create a method that selects all batches after a given date.
    static List<ProductionBatch> findByBatchDate(Date date);

    //9.Select Batches by Shampoos
    //Create a method that selects all batches without shampoos. Sort descending by date.
    List<ProductionBatch> findByShampoosIsNull();

    //20.Select Batch Date, Shampoo Label Title
    //Create a method that selects batch date and shampoo label title. Use named query.
    //it uses the named query in ProductionBatch.java
    //we need to put the returned values into an Object array and then access it
    List<Object[]> getDifferentFields();
}

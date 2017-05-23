package com.hibernate._hibernate_relations_01.dao;

import com.hibernate._hibernate_relations_01.entities.batches.ProductionBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionBatchDao extends JpaRepository<ProductionBatch, Long> {
}

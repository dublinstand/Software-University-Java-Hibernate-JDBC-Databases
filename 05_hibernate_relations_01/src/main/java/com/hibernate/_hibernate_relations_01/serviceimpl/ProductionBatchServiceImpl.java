package com.hibernate._hibernate_relations_01.serviceimpl;

import com.hibernate._hibernate_relations_01.dao.ProductionBatchDao;
import com.hibernate._hibernate_relations_01.entities.batches.ProductionBatch;
import com.hibernate._hibernate_relations_01.service.ProductionBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductionBatchServiceImpl implements ProductionBatchService {

    @Autowired
    private ProductionBatchDao productionBatchDao;


    @Override
    public void create(ProductionBatch productionBatch) {
        this.productionBatchDao.saveAndFlush(productionBatch);
    }
}

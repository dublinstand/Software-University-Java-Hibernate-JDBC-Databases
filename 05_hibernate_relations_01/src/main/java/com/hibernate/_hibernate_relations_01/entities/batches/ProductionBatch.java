package com.hibernate._hibernate_relations_01.entities.batches;

import com.hibernate._hibernate_relations_01.entities.shampoos.BasicShampoo;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "batches")
public class ProductionBatch implements Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "batch_date")
    private Date batchDate;

    //?
    private Set<BasicShampoo> shampoos;

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void setId(long id) {

    }

    @Override
    public Date getBatchDate() {
        return null;
    }

    @Override
    public void setBatchDate(Date batchDate) {

    }

    @Override
    public Set<BasicShampoo> getShampoos() {
        return null;
    }

    @Override
    public void setShampoos(Set<BasicShampoo> shampoos) {

    }
}

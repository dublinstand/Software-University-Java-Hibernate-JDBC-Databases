package com.hibernate._hibernate_relations_01.entities.shampoos;

import com.hibernate._hibernate_relations_01.entities.batches.ProductionBatch;
import com.hibernate._hibernate_relations_01.entities.enums.Size;
import com.hibernate._hibernate_relations_01.entities.labels.ClassicLabel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "PP")
public class PinkPanther extends BasicShampoo {

    private static final String BRAND = "Pink Panther";

    private static final BigDecimal PRICE = new BigDecimal("8.50");

    private static final Size SIZE = Size.MEDIUM;

    public PinkPanther() {
    }

    public PinkPanther(ClassicLabel label, ProductionBatch batch) {
        super(BRAND, PRICE, SIZE, label, batch);
    }
}

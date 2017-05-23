package com.hibernate._hibernate_relations_01.entities.shampoos;

import com.hibernate._hibernate_relations_01.entities.batches.ProductionBatch;
import com.hibernate._hibernate_relations_01.entities.enums.Size;
import com.hibernate._hibernate_relations_01.entities.labels.ClassicLabel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "FN")
public class FreshNuke extends BasicShampoo {

    private static final String BRAND = "Fresh Nuke";

    private static final BigDecimal PRICE = new BigDecimal("9.99");

    private static final Size SIZE = Size.LARGE;

    public FreshNuke() {
    }

    public FreshNuke(ClassicLabel label, ProductionBatch batch) {
        super(BRAND, PRICE, SIZE, label, batch);
    }
}

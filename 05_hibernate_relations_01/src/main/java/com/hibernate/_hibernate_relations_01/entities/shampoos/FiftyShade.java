package com.hibernate._hibernate_relations_01.entities.shampoos;

import com.hibernate._hibernate_relations_01.entities.batches.ProductionBatch;
import com.hibernate._hibernate_relations_01.entities.enums.Size;
import com.hibernate._hibernate_relations_01.entities.labels.ClassicLabel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "FS")
public class FiftyShade extends BasicShampoo {

    private static final String BRAND = "Fifty Shades";

    private static final BigDecimal PRICE = new BigDecimal("6.69");

    private static final Size SIZE = Size.SMALL;

    public FiftyShade() {
    }

    public FiftyShade(ClassicLabel label, ProductionBatch batch) {
        super(BRAND, PRICE, SIZE, label, batch);
    }
}

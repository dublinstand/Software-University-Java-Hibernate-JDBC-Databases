package com.hibernate._hibernate_relations_01.entities.shampoos;

import com.hibernate._hibernate_relations_01.entities.batches.ProductionBatch;
import com.hibernate._hibernate_relations_01.entities.enums.Size;
import com.hibernate._hibernate_relations_01.entities.ingredients.BasicIngredient;
import com.hibernate._hibernate_relations_01.entities.labels.ClassicLabel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public interface Shampoo extends Serializable {

    long getId();

    void setId(long id);

    String getBrand();

    void setBrand(String brand);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Size getSize();

    void setSize(Size size);

    ClassicLabel getLabel();

    void setLabel(ClassicLabel label);

    ProductionBatch getBatch();

    void setBatch(ProductionBatch batch);

    Set<BasicIngredient> getIngredients();

    void setIngredients(Set<BasicIngredient> ingredients);
}


package com.hibernate._hibernate_relations_01.entities.ingredients;


import java.io.Serializable;
import java.math.BigDecimal;

public interface Ingredients extends Serializable{

    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

}

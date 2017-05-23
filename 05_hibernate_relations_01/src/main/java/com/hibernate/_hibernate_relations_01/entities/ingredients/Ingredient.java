package com.hibernate._hibernate_relations_01.entities.ingredients;


import com.hibernate._hibernate_relations_01.entities.shampoos.BasicShampoo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

//we have all setters and getters in the interface
public interface Ingredient extends Serializable{

    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Set<BasicShampoo> getShampoos();

    void setShampoos(Set<BasicShampoo> shampoos);


}

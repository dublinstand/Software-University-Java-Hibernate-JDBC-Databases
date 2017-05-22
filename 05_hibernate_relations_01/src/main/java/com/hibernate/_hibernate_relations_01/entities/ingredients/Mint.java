package com.hibernate._hibernate_relations_01.entities.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "MN")
public class Mint extends BasicIngredient {

    //we have constant value for the NAME, that will be linked to the name column in the database
    private static final String NAME = "Mint";

    //we have constant for PRICE - it is unique
    private static final BigDecimal PRICE = new BigDecimal("3.54");

    //we set the values using the constructor and super - which means we use the constructor of the class that is
    //extended protected
    //      BasicIngredient(String name, BigDecimal price){
    //          this.name = name;
    //          this.price = price;
    public Mint() {
        super(NAME, PRICE);
    }

}

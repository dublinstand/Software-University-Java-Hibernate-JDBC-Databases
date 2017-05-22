package com.hibernate._hibernate_relations_01.entities.ingredients;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "NT")
public class Nettle extends BasicIngredient{

    //we have constant value for the NAME, that will be linked to the name column in the database
    private static final String NAME = "Nettle";

    //we have constant for PRICE - it is unique
    private static final BigDecimal PRICE = new BigDecimal("6.12");

    //we set the values using the constructor and super - which means we use the constructor of the class that is
    //extended protected
    //      BasicIngredient(String name, BigDecimal price){
    //          this.name = name;
    //          this.price = price;
    public Nettle() {
        super(NAME, PRICE);
    }
}

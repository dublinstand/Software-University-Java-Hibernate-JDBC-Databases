package com.hibernate._hibernate_relations_01.entities.ingredients;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

//@DiscriminatorValue(value = "ST") - this way we say it is the Strawberry class

@Entity
@DiscriminatorValue(value = "ST")
public class Strawberry extends BasicIngredient{

    //we have constant value for the NAME, that will be linked to the name column in the database
    private static final String NAME = "Strawberry";

    //we have constant for PRICE - it is unique for Strawberry class
    private static final BigDecimal PRICE = new BigDecimal("4.85");

    //we set the values using the constructor and super - which means we use the constructor of the class that is
    //extended protected
    // BasicIngredient(String name, BigDecimal price){
    //    this.name = name;
    //    this.price = price;
    public Strawberry() {
        super(NAME, PRICE);
    }



}

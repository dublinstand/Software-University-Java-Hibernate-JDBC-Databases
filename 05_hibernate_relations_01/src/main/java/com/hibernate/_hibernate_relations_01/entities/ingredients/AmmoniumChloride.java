package com.hibernate._hibernate_relations_01.entities.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "AM")
public class AmmoniumChloride extends BasicChemicalIngredient {

    //we have constant value for the NAME, that will be linked to the name column in the database
    private static final String NAME = "AmmoniumChloride";

    //we have constant for PRICE - it is unique
    private static final BigDecimal PRICE = new BigDecimal("0.59");

    private static final String CHEMICAL_FORMULA = "NH4Cl";

    //we use the constructor of the class we extend - BasicChemicalIngredient
    public AmmoniumChloride() {
        super(NAME, PRICE, CHEMICAL_FORMULA);
    }
}

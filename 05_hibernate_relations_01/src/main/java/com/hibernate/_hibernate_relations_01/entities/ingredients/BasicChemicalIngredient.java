package com.hibernate._hibernate_relations_01.entities.ingredients;


import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public abstract class BasicChemicalIngredient extends BasicIngredient implements ChemicalIngredient{

    @Column(name = "chemical_formula")
    private String chemicalFormula;

    protected BasicChemicalIngredient() {

    }

    //here we use the constructor of the class we extend - BasicIngredient to pass name and price
    //and we use the setter to enter the new table column chemical_formula
    //it will be used by the entities that extend this abstract class
    protected BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula) {
        super(name, price);
        this.setChemicalFormula(chemicalFormula);
    }

    @Override
    public String getChemicalFormula() {
        return chemicalFormula;
    }

    @Override
    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }
}

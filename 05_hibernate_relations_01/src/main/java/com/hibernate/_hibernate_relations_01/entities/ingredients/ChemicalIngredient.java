package com.hibernate._hibernate_relations_01.entities.ingredients;


//we have the extra getters and setters for the BasicChemicalIngredient abstract class
public interface ChemicalIngredient extends Ingredient {

    String getChemicalFormula();

    void setChemicalFormula(String chemicalFormula);

}

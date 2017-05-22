package com.hibernate._hibernate_relations_01.entities.ingredients;



public interface ChemicalIngredient extends Ingredient {

    String getChemicalFormula();

    void setChemicalFormula(String chemicalFormula);

}

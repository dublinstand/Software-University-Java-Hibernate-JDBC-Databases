package com.neckandelbows.dao;

import com.neckandelbows.domain.ingredients.BasicChemicalIngredient;
import com.neckandelbows.domain.ingredients.BasicIngredient;
import org.springframework.stereotype.Repository;

import java.util.List;

//here we extend IngredientRepository and pass in it BasicChemicalIngredient to the generic there
@Repository
public interface ChemicalIngredientRepository  extends IngredientRepository<BasicChemicalIngredient>{
    List<BasicIngredient> findByChemicalFormula(String chemicalFormula);
}

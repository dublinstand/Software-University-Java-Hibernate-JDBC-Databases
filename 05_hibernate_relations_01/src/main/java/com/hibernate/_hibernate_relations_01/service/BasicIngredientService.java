package com.hibernate._hibernate_relations_01.service;

import com.hibernate._hibernate_relations_01.entities.ingredients.BasicIngredient;

public interface BasicIngredientService {

    void create (BasicIngredient basicIngredient);

    BasicIngredient retrieve (long id);
}

package com.hibernate._hibernate_relations_01.terminal;

import com.hibernate._hibernate_relations_01.entities.ingredients.AmmoniumChloride;
import com.hibernate._hibernate_relations_01.entities.ingredients.BasicIngredient;
import com.hibernate._hibernate_relations_01.entities.ingredients.Ingredient;
import com.hibernate._hibernate_relations_01.entities.ingredients.Mint;
import com.hibernate._hibernate_relations_01.service.BasicIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner{

    @Autowired
    private BasicIngredientService basicIngredientService;

    @Override
    public void run(String... strings) throws Exception {

        BasicIngredient ammoniumChloride = new AmmoniumChloride();
        BasicIngredient mint = new Mint();

        basicIngredientService.create(ammoniumChloride);
        basicIngredientService.create(mint);
        Ingredient ingredient = basicIngredientService.retrieve(1);
    }
}

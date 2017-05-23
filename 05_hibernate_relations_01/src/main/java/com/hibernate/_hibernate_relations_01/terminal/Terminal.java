package com.hibernate._hibernate_relations_01.terminal;

import com.hibernate._hibernate_relations_01.entities.batches.ProductionBatch;
import com.hibernate._hibernate_relations_01.entities.ingredients.*;
import com.hibernate._hibernate_relations_01.entities.labels.ClassicLabel;
import com.hibernate._hibernate_relations_01.entities.shampoos.BasicShampoo;
import com.hibernate._hibernate_relations_01.entities.shampoos.FiftyShade;
import com.hibernate._hibernate_relations_01.entities.shampoos.FreshNuke;
import com.hibernate._hibernate_relations_01.service.BasicIngredientService;
import com.hibernate._hibernate_relations_01.service.BasicShampooService;
import com.hibernate._hibernate_relations_01.service.ClassicLabelService;
import com.hibernate._hibernate_relations_01.service.ProductionBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Terminal implements CommandLineRunner{

    @Autowired
    private BasicIngredientService basicIngredientService;

    @Autowired
    private BasicShampooService basicShampooService;

    @Autowired
    private ProductionBatchService productionBatchService;

    @Autowired
    private ClassicLabelService classicLabelService;

    @Override
    public void run(String... strings) throws Exception {

        BasicIngredient ammoniumChloride = new AmmoniumChloride();
        BasicIngredient mint = new Mint();
        BasicIngredient nettle = new Nettle();

        basicIngredientService.create(ammoniumChloride);
        basicIngredientService.create(mint);
        basicIngredientService.create(nettle);

        ProductionBatch productionBatch = new ProductionBatch(new Date());
        this.productionBatchService.create(productionBatch);

        ClassicLabel classicLabelOne = new ClassicLabel("Awesome Title", "Subtitle");
        this.classicLabelService.create(classicLabelOne);

        ClassicLabel classicLabelTwo = new ClassicLabel("Awesome 2 ", "Subtitle 2");
        this.classicLabelService.create(classicLabelTwo);

        BasicShampoo basicShampooOne = new FreshNuke(classicLabelOne, productionBatch);
        basicShampooOne.getIngredients().add(ammoniumChloride);
        basicShampooOne.getIngredients().add(nettle);

        BasicShampoo basicShampooTwo = new FiftyShade(classicLabelTwo, productionBatch);
        basicShampooTwo.getIngredients().add(ammoniumChloride);
        basicShampooTwo.getIngredients().add(mint);

        this.basicShampooService.create(basicShampooOne);
        this.basicShampooService.create(basicShampooTwo);

//        Ingredient ingredient = basicIngredientService.retrieve(1);
    }
}

package com.neckandelbows.terminal;

import com.neckandelbows.dao.*;
import com.neckandelbows.domain.enums.Size;
import com.neckandelbows.domain.ingredients.Ingredient;
import com.neckandelbows.domain.labels.ClassicLabel;
import com.neckandelbows.domain.shampoos.BasicShampoo;
import com.neckandelbows.service.BasicIngredientService;
import com.neckandelbows.service.BasicShampooService;
import com.neckandelbows.service.ClassicLabelService;
import com.neckandelbows.service.ProductionBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    private BasicIngredientService basicIngredientService;

    @Autowired
    private BasicShampooService basicShampooService;

    @Autowired
    private ProductionBatchService productionBatchService;

    @Autowired
    private ClassicLabelService classicLabelService;

    @Autowired
    private BasicShampooDao basicShampooDao;

    @Autowired
    private ClassicLabelDao classicLabelDao;

    @Autowired
    private BasicIngredientDao basicIngredientDao;

    @Autowired
    private ProductionBatchDao productionBatchDao;

    @Autowired
    private ChemicalIngredientRepository chemicalIngredientRepository;

    @Override
    public void run(String... strings) throws Exception {

//        BasicIngredient am = new AmmoniumChloride();
//        BasicIngredient mint = new Mint();
//        BasicIngredient nettle = new Nettle();
//        this.basicIngredientService.create(am);
//        this.basicIngredientService.create(mint);
//        this.basicIngredientService.create(nettle);
//
//        ProductionBatch productionBatch = new ProductionBatch(new Date());
//        this.productionBatchService.create(productionBatch);
//
//        ClassicLabel classicLabelOne = new ClassicLabel("Aweseom Tittle", "Subtitle");
//        this.classicLabelService.create(classicLabelOne);
//
//        ClassicLabel classicLabelTwo = new ClassicLabel("Aweseom 2", "Subtitle2");
//        this.classicLabelService.create(classicLabelTwo);
//
//        BasicShampoo basicShampooOne = new FreshNuke(classicLabelOne,productionBatch);
//        basicShampooOne.getIngredients().add(am);
//        basicShampooOne.getIngredients().add(nettle);
//
//        BasicShampoo basicShampooTwo = new FiftyShade(classicLabelTwo,productionBatch);
//        basicShampooTwo.getIngredients().add(am);
//        basicShampooTwo.getIngredients().add(mint);
//
//        this.basicShampooService.create(basicShampooOne);
//        this.basicShampooService.create(basicShampooTwo);


        //1. Select Shampoos by Brand
        //Create a method that selects all shampoos by brand.
        List<BasicShampoo> shampoosByBrand = basicShampooDao.findByBrand("Fresh Nuke");
        for (BasicShampoo basicShampoo : shampoosByBrand) {
            System.out.println(basicShampoo.getBrand());
        }

        //2.Select Shampoos by Brand and Size
        //Create a method that selects all shampoos by brand and size.
        List<BasicShampoo> shampoosByBrandAndSize = basicShampooDao.findByBrandAndSize("Fresh Nuke", Size.LARGE);
        for (BasicShampoo shampoo : shampoosByBrandAndSize) {
            System.out.println(shampoo.getBrand());
        }

        //3.Select Shampoos by Size or Label
        //Create a method that selects all shampoos by size or label. Sort ascending by price.
        //In the terminal you need to use the implementation for Label - ClassicLabel
        //Because it doesn't have an ID the query won't work because we look by ID
        //that's why we need to set an ID or get it from the database
        // ClassicLabel classicLabel = new ClassicLabel();
        //classicLabel.setId(100L);
        //Or you can get a label from the database, not that we need to add L in the end to have it as Long
        ClassicLabel classicLabel1 = classicLabelDao.findOne(1L);
        List<BasicShampoo> shampoosByLabelOrSize = basicShampooDao.findByLabelOrSizeOrderByPriceAsc(classicLabel1, Size.LARGE);
        for (BasicShampoo basicShampoo : shampoosByLabelOrSize) {
            System.out.println(basicShampoo.getBrand());
        }

        //4.Select Shampoos by Price
        //Create a method that selects all shampoos higher than a given price. Sort descending by brand
        List<BasicShampoo> shampoosByPrice = basicShampooDao.findByPriceGreaterThanOrderByBrandAsc(new BigDecimal(1));
        for (BasicShampoo basicShampoo : shampoosByPrice){
            System.out.println(basicShampoo.getPrice());
        }

        //5.Select Batches by Date
        //Create a method that selects all batches after a given date.
        ProductionBatchDao.findByBatchDate(new Date());

        //6.Select Ingredients without Price
        //Create a method that selects all ingredients without price. Sort descending by name and price.
        basicIngredientDao.findByPriceIsNull();

        //7.Select Ingredients by Name
        //Create a method that selects all ingredients which name starts with given letters.
        List<Ingredient> ingredientsStartingWith = basicIngredientDao.findByNameStartsWith("n");
        for (Ingredient ingredient : ingredientsStartingWith){
            System.out.println(ingredient.getName());
        }

        //8.Select Ingredients by Names
        //Create a method that selects all ingredients which are contained in a given list. Sort ascending by price.
        //we add the list in the method itself creating new ArrayList and adding the values
        List<Ingredient> ingredientsByListOfNames =
                basicIngredientDao.findByNameInOrderByPriceAsc(new ArrayList(){{add("Mint");add("Nettle");}});
        for (Ingredient ingredient : ingredientsByListOfNames){
            System.out.println(ingredient.getName());
        }

        //9.Select Batches by Shampoos
        //Create a method that selects all batches without shampoos. Sort descending by date.
        productionBatchDao.findByShampoosIsNull();

        //10.Count Shampoos by Price
        //Create a method that counts all shampoos with price lower than a given price.
        int countShampoosLessThanGivenPrice = basicShampooDao.countByPriceLessThan(new BigDecimal("20.3"));
        System.out.println(countShampoosLessThanGivenPrice);


        //11.Select Shampoos by Label. JPQL query
        //Create a method that selects all shampoos by label.
        //the comparison will occur by label_id
        ClassicLabel classicLabel2 = classicLabelDao.findOne(2L);
        List<BasicShampoo> shampoosByLabel = basicShampooDao.getByLabel(classicLabel2);
        for (BasicShampoo shampoo : shampoosByLabel){
            System.out.println(shampoo.getBrand());
        }

        //13.Select Shampoos by Ingredients
        //Create a method that selects all shampoos with ingredients in a given list.
        List<BasicShampoo> shampoosByListOfIngredientsNames = basicShampooDao.getByIngredients(new ArrayList(){{add("Nettle"); add("Mint");}});
        for (BasicShampoo shampoo : shampoosByListOfIngredientsNames){
            System.out.println(shampoo.getBrand());
        }

        //16.Select Shampoos by Ingredients Sum of Price
        //Create a method that selects all shampoos in with ingredients which sum is less than a given number.
        //we use GROUP BY id and HAVING the sum
        basicShampooDao.getByIngredientPrice(new BigDecimal("10"));

        //20.Select Batch Date, Shampoo Label Title
        //Create a method that selects batch date and shampoo label title. Use named query.
        List<Object[]> objects = productionBatchDao.getDifferentFields();
        for (Object[] object : objects){
            Date batchDate = (Date) object[0];
            String title = (String) object[1];
            System.out.println(batchDate + " " + title);
        }


        //21.Delete Ingredients by name
        //Create a method that deletes ingredients by a given name. Use named query.
        basicIngredientDao.deleteByName("Mint");

        //22.Update Ingredients by price
        //Create a method that increases the price of all ingredients by 10%. Use named query.
        basicIngredientDao.updateAllPrices();

        //Advanced - find by chemical formula from BasicChemicalIngredient
        chemicalIngredientRepository.findByChemicalFormula("NH4Cl");
    }
}

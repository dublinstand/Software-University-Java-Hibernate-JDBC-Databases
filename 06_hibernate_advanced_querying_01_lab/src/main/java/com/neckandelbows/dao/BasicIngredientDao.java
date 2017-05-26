package com.neckandelbows.dao;

import com.neckandelbows.domain.ingredients.BasicIngredient;
import com.neckandelbows.domain.ingredients.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BasicIngredientDao extends JpaRepository<BasicIngredient, Long> {


    //6.Select Ingredients without Price
    //Create a method that selects all ingredients without price. Sort descending by name and price.
    List<Ingredient> findByPriceIsNull();

    //7.Select Ingredients by Name
    //Create a method that selects all ingredients which name starts with given letters.
    List<Ingredient> findByNameStartsWith(String nameStartingWith);

    //8.Select Ingredients by Names
    //Create a method that selects all ingredients which are contained in a given list. Sort ascending by price.
    List<Ingredient> findByNameInOrderByPriceAsc(List<String> names);

    //21.Delete Ingredients by name
    //Create a method that deletes ingredients by a given name. Use named query.
    //it refers to the JPQL in BasicIngredient named query
    //if we modify something in the database we need to have @Modifying
    //we need also have the @Transactional annotation in order to commit the transaction - whenever we do a DML queries
    //or you can put @Transactional below the repository
    @Modifying
    @Transactional
    void deleteByName(@Param(value = "name") String name);

    //22.Update Ingredients by price
    //Create a method that increases the price of all ingredients by 10%. Use named query.
    //Add @Modifying as well, @Transactional we have in the beginning of the interface
    @Modifying
    @Query(value = "UPDATE BasicIngredient AS b " +
            "SET b.price = b.price * 1.10")
    void updateAllPrices();
}

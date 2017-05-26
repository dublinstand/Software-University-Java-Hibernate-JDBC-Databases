package com.neckandelbows.dao;

import com.neckandelbows.domain.enums.Size;
import com.neckandelbows.domain.labels.ClassicLabel;
import com.neckandelbows.domain.labels.Label;
import com.neckandelbows.domain.shampoos.BasicShampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BasicShampooDao extends JpaRepository<BasicShampoo, Long>{

    //1.Select Shampoos by Brand
    //Create a method that selects all shampoos by brand.
    List<BasicShampoo> findByBrand(String brand);

    //2.Select Shampoos by Brand and Size
    //Create a method that selects all shampoos by brand and size.
    List<BasicShampoo> findByBrandAndSize(String brand, Size size);

    //3.Select Shampoos by Size or Label
    //Create a method that selects all shampoos by size or label. Sort ascending by price.
    //here you can use the Interface Label, but if you use CRUD operations you need to use the implementation ClassicLable
    List<BasicShampoo> findByLabelOrSizeOrderByPriceAsc(Label label, Size size);

    //4.Select Shampoos by Price
    //Create a method that selects all shampoos higher than a given price. Sort descending by brand
    List<BasicShampoo> findByPriceGreaterThanOrderByBrandAsc(BigDecimal price);

    //10.Count Shampoos by Price
    //Create a method that counts all shampoos with price lower than a given price.
    int countByPriceLessThan(BigDecimal bigDecimal);

    //11.Select Shampoos by Label. JPQL query
    //Create a method that selects all shampoos by label.
    //the comparison will occur by label_id
    //with annotation @Param in the getLabel method we say which our parameter is
    @Query(value = "SELECT s FROM BasicShampoo AS s WHERE s.label = :labelParam")
    List<BasicShampoo> getByLabel(@Param(value = "labelParam") ClassicLabel classicLabel);

    //13.Select Shampoos by Ingredients.JPQL query
    //Create a method that selects all shampoos with ingredients in a given list.
    //we select all from BasicShampoo and have INNER JOIN with the ingredients table (BasicIngredient.class)
    //here we have a list of names passed as parameters. In JPQL is done with i.name IN :names,
    //we also say with @Param which our parameters are
    @Query(value = "SELECT s FROM BasicShampoo AS s INNER JOIN s.ingredients AS i WHERE i.name IN :names")
    List<BasicShampoo> getByIngredients(@Param(value = "names") List<String> names);

    //16.Select Shampoos by Ingredients Sum of Price
    //Create a method that selects all shampoos in with ingredients which sum is less than a given number.
    @Query(value = "SELECT s FROM BasicShampoo AS s INNER JOIN s.ingredients AS i GROUP BY s HAVING SUM(i.price) < :price")
    List<BasicShampoo> getByIngredientPrice(@Param(value = "price") BigDecimal price);
}

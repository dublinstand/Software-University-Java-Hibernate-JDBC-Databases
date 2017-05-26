package com.neckandelbows.domain.ingredients;

import com.neckandelbows.domain.shampoos.BasicShampoo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

//21.Delete Ingredients by name
//Create a method that deletes ingredients by a given name. Use named query.



@Entity
@Table(name = "igredients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ingredient_type", discriminatorType = DiscriminatorType.STRING)
@NamedQuery(name = "BasicIngredient.deleteByName",
        query = "DELETE FROM BasicIngredient AS b " +
                "WHERE b.name = :name")
public abstract class BasicIngredient implements Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(mappedBy = "ingredients")
    private Set<BasicShampoo> shampoos;

    protected BasicIngredient() {
        this.setShampoos(new HashSet<>());
    }

    protected BasicIngredient(String name, BigDecimal price) {
        this();
        this.setName(name);
        this.setPrice(price);
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public Set<BasicShampoo> getShampoos() {
        return this.shampoos;
    }

    @Override
    public void setShampoos(Set<BasicShampoo> shampoos) {
        this.shampoos = shampoos;
    }
}
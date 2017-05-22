package com.hibernate._hibernate_relations_01.entities.ingredients;


import javax.persistence.*;
import java.math.BigDecimal;

//we use single table strategy - @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//in @DiscriminatorColumn we say which column is unique for its inheritors
//@DiscriminatorColumn(name = "ingredient_type", discriminatorType = DiscriminatorType.STRING)
//we say its value will be string
//we have the class as abstract, because it will be extended by all classes below him

@Entity
@Table(name = "ingredients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ingredient_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicIngredient implements Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;


    //because the class is abstract, its constructors must be protected - means they can be only used
    //by their inheritors
    protected BasicIngredient() {
    }

    //because the class is abstract, its constructors must be protected - means they can be only used
    //by their inheritors
    protected BasicIngredient(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

package com.hibernate._hibernate_relations_01.entities.ingredients;


import com.hibernate._hibernate_relations_01.entities.shampoos.BasicShampoo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

    //in order to have two way relation
    //we map the set shampoos to BasicShampoo and its field ingredients
    @ManyToMany(mappedBy = "ingredients")
    private Set<BasicShampoo> shampoos;


    //because the class is abstract, its constructors must be protected - means they can be only used
    //by their inheritors
    //this way we initialize the Set<BasicShampoo>
    protected BasicIngredient() {
        this.setShampoos(new HashSet<>());
    }

    //because the class is abstract, its constructors must be protected - means they can be only used
    //by their inheritors
    protected BasicIngredient(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }

    public void addBasicShampoo(BasicShampoo basicShampoo){
        this.getShampoos().add(basicShampoo);
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

    @Override
    public Set<BasicShampoo> getShampoos() {
        return this.shampoos;
    }

    @Override
    public void setShampoos(Set<BasicShampoo> shampoos) {
        this.shampoos = shampoos;
    }
}

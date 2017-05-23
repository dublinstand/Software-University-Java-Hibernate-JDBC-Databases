package com.hibernate._hibernate_relations_01.entities.shampoos;

import com.hibernate._hibernate_relations_01.entities.batches.ProductionBatch;
import com.hibernate._hibernate_relations_01.entities.enums.Size;
import com.hibernate._hibernate_relations_01.entities.ingredients.Ingredient;
import com.hibernate._hibernate_relations_01.entities.labels.ClassicLabel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(columnDefinition = "shampoo_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "shampoos")
public class BasicShampoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private String brand;

    @Basic
    private BigDecimal price;

    //we link the field to the enumerated class Size and say that we expect a String
    @Enumerated(EnumType.STRING)
    private Size size;

    //one shampoo has one label;
    //in order to check if we have entered value at runtime we set optional = false
    //our column is called "label_id" and its constraint is in ClassicLabel with column = "id"
    @OneToOne(optional = false)
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    private ClassicLabel classicLabel;

    //here we have many shampoos to one batch
    @ManyToOne(optional = false)
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    private ProductionBatch productionBatch;

    //many ingredients will be used in one shampoo and one shampoo will use many ingredients
    //here again we have @JoinTable - we create a table with name = "shampoo_ingredients",
    //it gets the id of the current class (BasicShampoo) and it is linked to "shampoo_id" in the shampoo_ingredients,
    //then we have an inverseJoinColumns with name of the column "ingredient_id" that is linked to "id" in Ingredient table
    @ManyToMany
    @JoinTable(name = "shampoos_ingredients",
    joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<Ingredient> ingredients;

}

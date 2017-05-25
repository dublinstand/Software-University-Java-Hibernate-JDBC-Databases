package com.neckandelbows.domain.shampoos;


import com.neckandelbows.domain.batches.ProductionBatch;
import com.neckandelbows.domain.enums.Size;
import com.neckandelbows.domain.ingredients.BasicIngredient;
import com.neckandelbows.domain.labels.ClassicLabel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shampoo_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicShampoo implements Shampoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
    private ClassicLabel label;

    //here we have many shampoos to one batch
    @ManyToOne(optional = false)
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    private ProductionBatch batch;

    //many ingredients will be used in one shampoo and one shampoo will use many ingredients
    //here again we have @JoinTable - we create a table with name = "shampoo_ingredients",
    //it gets the id of the current class (BasicShampoo) and it is linked to "shampoo_id" in the shampoo_ingredients,
    //then we have an inverseJoinColumns with name of the column "ingredient_id" that is linked to "id" in Ingredient table
    @ManyToMany
    @JoinTable(name = "shampoos_ingredients",
            joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;

    //protected because it is an abstract class
    //we instantiate the set here
    protected BasicShampoo() {
        this.setIngredients(new HashSet<>());
    }

    protected BasicShampoo(String brand, BigDecimal price, Size size, ClassicLabel label, ProductionBatch batch) {
        this();
        this.setBrand(brand);
        this.setPrice(price);
        this.setSize(size);
        this.setLabel(label);
        this.setBatch(batch);
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
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
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
    public Size getSize() {
        return size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public ClassicLabel getLabel() {
        return label;
    }

    @Override
    public void setLabel(ClassicLabel label) {
        this.label = label;
    }

    @Override
    public ProductionBatch getBatch() {
        return batch;
    }

    @Override
    public void setBatch(ProductionBatch batch) {
        this.batch = batch;
    }

    @Override
    public Set<BasicIngredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public void setIngredients(Set<BasicIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}

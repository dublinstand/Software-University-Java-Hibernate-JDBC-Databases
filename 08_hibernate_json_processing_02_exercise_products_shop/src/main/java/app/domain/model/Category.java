package app.domain.model;

import javax.persistence.*;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "category_products",
            joinColumns = @JoinColumn(name = "category_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> categoryProducts;

    public Category() {
        this.setProducts(new HashSet<>());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() < 3 && name.length() > 15){
            throw new InvalidParameterException("Category Name should be between 3 and 15 characters");
        }

        this.name = name;
    }

    public Set<Product> getProducts() {
        return categoryProducts;
    }

    public void setProducts(Set<Product> products) {
        this.categoryProducts = products;
    }
}

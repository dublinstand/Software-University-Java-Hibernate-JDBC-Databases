package app.repository;

import app.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product AS p WHERE p.price > 500 and p.price < 1000 and p.buyer IS NULL")
    List<Product> getProductsBetween500And1000WithNoBuyer();
}

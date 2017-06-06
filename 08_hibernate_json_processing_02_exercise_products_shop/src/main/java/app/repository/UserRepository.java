package app.repository;

import app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM Product AS p INNER JOIN p.seller AS u WHERE p.buyer IS NOT NULL")
    List<User> findUserWithSoldProducts();


}

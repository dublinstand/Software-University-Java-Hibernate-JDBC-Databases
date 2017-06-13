package com.jsonexercise.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jsonexercise.domain.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT u FROM User AS u " +
            "INNER JOIN u.soldProducts AS p " +
            "WHERE p.buyer IS NOT NULL " +
            "GROUP BY u " +
            "HAVING COUNT(p.id) >= 1 " +
            "ORDER BY u.firstName, u.lastName")
    List<User> findWithMoreThanOneBuyer();
}
package com.hibernate._hibernate_relations_01.dao;

import com.hibernate._hibernate_relations_01.entities.ingredients.BasicIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicIngredientDao extends JpaRepository<BasicIngredient, Long> {
}

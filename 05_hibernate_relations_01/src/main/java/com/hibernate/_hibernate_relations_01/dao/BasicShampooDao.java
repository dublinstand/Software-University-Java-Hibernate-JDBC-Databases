package com.hibernate._hibernate_relations_01.dao;

import com.hibernate._hibernate_relations_01.entities.shampoos.BasicShampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicShampooDao extends JpaRepository<BasicShampoo, Long> {
}

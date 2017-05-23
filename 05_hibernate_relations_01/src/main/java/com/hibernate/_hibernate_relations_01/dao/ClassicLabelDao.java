package com.hibernate._hibernate_relations_01.dao;

import com.hibernate._hibernate_relations_01.entities.labels.ClassicLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassicLabelDao extends JpaRepository<ClassicLabel, Long> {
}

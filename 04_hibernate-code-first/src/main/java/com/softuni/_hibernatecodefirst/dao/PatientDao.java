package com.softuni._hibernatecodefirst.dao;

import com.softuni._hibernatecodefirst.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PatientDao extends JpaRepository<Patient, Long> {
}

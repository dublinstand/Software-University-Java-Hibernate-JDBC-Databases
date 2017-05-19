package com.softuni._hibernatecodefirst.repository;


import com.softuni._hibernatecodefirst.entities.WizardDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WizardDepositRepository extends JpaRepository<WizardDeposit, Long> {

    @Override
    @Query("SELECT wd FROM WizardDeposit AS wd WHERE wd.isDepositExpired=false")
    List<WizardDeposit> findAll();
}

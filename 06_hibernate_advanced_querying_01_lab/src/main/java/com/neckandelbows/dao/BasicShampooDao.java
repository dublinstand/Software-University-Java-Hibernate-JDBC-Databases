package com.neckandelbows.dao;

import com.neckandelbows.domain.shampoos.BasicShampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicShampooDao extends JpaRepository<BasicShampoo, Long>{

    //1.	Select Shampoos by Brand
    //Create a method that selects all shampoos by brand.
    List<BasicShampoo> findByBrand (String brand);

}

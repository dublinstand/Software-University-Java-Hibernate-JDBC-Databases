package com.jsonexercise.services.product;

import com.jsonexercise.domain.dto.ProductBuyerDto;
import com.jsonexercise.domain.dto.ProductDto;

import java.util.List;

public interface ProductService {

    void create (ProductDto productDto);

    List<ProductBuyerDto> findWithoutBuyer();
}
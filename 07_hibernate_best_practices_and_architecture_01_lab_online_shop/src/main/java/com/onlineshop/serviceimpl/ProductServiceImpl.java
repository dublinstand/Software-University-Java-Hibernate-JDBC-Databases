package com.onlineshop.serviceimpl;

import com.onlineshop.domain.dto.ProductDto;
import com.onlineshop.domain.model.Product;
import com.onlineshop.factory.ProductFactory;
import com.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductFactory productFactory;

    @Override
    public Product create(ProductDto productDto) {
        String name = productDto.getName();
        BigDecimal price = productDto.getPrice();
        Product product = this.productFactory.create(name, price);
        return product;
    }
}

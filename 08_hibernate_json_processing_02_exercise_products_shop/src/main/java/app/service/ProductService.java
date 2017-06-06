package app.service;

import app.domain.dto.ProductDto;
import app.domain.dto.UserDto;
import app.domain.model.Product;
import app.domain.model.User;

import java.util.List;

public interface ProductService {

    void create(ProductDto productDto, User buyer, User seller);

    void createWithNoBuyer(ProductDto productDto, User seller);

    List<Product> findAll();

    List<ProductDto> getProductsBetween500And1000WithNoBuyer();


}

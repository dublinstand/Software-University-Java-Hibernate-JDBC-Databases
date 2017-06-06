package app.serviceimpl;

import app.domain.dto.ProductDto;
import app.domain.model.Product;
import app.domain.model.User;
import app.repository.ProductRepository;
import app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void create(ProductDto productDto, User buyer, User seller) {
        Product product = this.convertToEntity(productDto);
        product.setBuyer(buyer);
        product.setSeller(seller);

        this.productRepository.saveAndFlush(product);
    }

    @Override
    public void createWithNoBuyer(ProductDto productDto, User seller) {
        Product product = this.convertToEntity(productDto);
        product.setSeller(seller);

        this.productRepository.saveAndFlush(product);
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public List<ProductDto> getProductsBetween500And1000WithNoBuyer() {
        List<ProductDto> productDtos = new ArrayList<>();

        List<Product> products = this.productRepository.getProductsBetween500And1000WithNoBuyer();

        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setPrice(product.getPrice());

            productDto.setSeller(product.getSeller().getFirstName() + " " + product.getSeller().getLastName());

            productDtos.add(productDto);
        }
        return productDtos;
    }

    private Product convertToEntity(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        return product;
    }

    public ProductDto convertToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());

        return productDto;
    }


}

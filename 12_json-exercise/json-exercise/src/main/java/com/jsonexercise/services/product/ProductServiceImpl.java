package com.jsonexercise.services.product;

import com.jsonexercise.domain.dto.ProductBuyerDto;
import com.jsonexercise.domain.dto.ProductDto;
import com.jsonexercise.domain.entities.Category;
import com.jsonexercise.domain.entities.Product;
import com.jsonexercise.domain.entities.User;
import com.jsonexercise.parser.ModelParser;
import com.jsonexercise.repositories.CategoryRepository;
import com.jsonexercise.repositories.ProductRepository;
import com.jsonexercise.repositories.UserRepository;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ModelParser modelParser;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void create(ProductDto productDto) {
		Product product = this.modelParser.convert(productDto, Product.class);
		product.setBuyer(this.getRandomUser());
		product.setSeller(this.getRandomUser());
		Set<Category> categories = this.getRandomCategories();
        product.setCategories(categories);
        this.productRepository.save(product);
	}

    @Override
    public List<ProductBuyerDto> findWithoutBuyer() {
        List<Product> products = this.productRepository.findWithoutBuyer();
        List<ProductBuyerDto> productDtos = this.modelParser.convert(products, ProductBuyerDto.class);
        return productDtos;
    }


    private User getRandomUser(){
		long usersCount = this.userRepository.count();
		long randomId = ThreadLocalRandom.current().nextLong(1, usersCount + 100);
		User user = this.userRepository.findOne(randomId);
		return user;
	}

	private Set<Category> getRandomCategories(){
		Set<Category> categories = new HashSet<>();
		int categotyListSize = ThreadLocalRandom.current().nextInt(1, 5);
		long categoiesCount = this.categoryRepository.count();
		for (int i = 0; i < categotyListSize; i++) {
			long randomId = ThreadLocalRandom.current().nextLong(1, categoiesCount +1);
			Category category = this.categoryRepository.findOne(randomId);
			categories.add(category);
		}

		return categories;
	}
}
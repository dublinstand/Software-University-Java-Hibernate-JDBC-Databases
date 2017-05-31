package com.onlineshop.configuration;

import com.onlineshop.factory.LineItemFactory;
import com.onlineshop.factory.LocationFactory;
import com.onlineshop.factory.OrderFactory;
import com.onlineshop.factory.ProductFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public ProductFactory productFactory(){
        return new ProductFactory();
    }

    @Bean
    public LineItemFactory lineItemFactory(){
        return new LineItemFactory();
    }

    @Bean
    public OrderFactory orderFactoryFactory(){
        return new OrderFactory();
    }

    @Bean
    public LocationFactory locationFactory(){
        return new LocationFactory();
    }
}

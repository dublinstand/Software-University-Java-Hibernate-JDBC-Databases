package com.onlineshop.controller;

import com.onlineshop.domain.dto.ProductDto;
import com.onlineshop.domain.model.LineItem;
import com.onlineshop.domain.model.Location;
import com.onlineshop.domain.model.Order;
import com.onlineshop.domain.model.Product;
import com.onlineshop.service.LineItemService;
import com.onlineshop.service.LocationService;
import com.onlineshop.service.OrderService;
import com.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class OnlineShopController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private LineItemService lineItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private LocationService locationService;

    //will return a view
    @GetMapping(value = "/shop")
    public String getShop(Model model){
        model.addAttribute("productDto", new ProductDto());
        return "shop";
    }

    //will persist the data passed from the form
    //we have all methods outside of this method
    @PostMapping("/shop")
    public String shopSubmit(@ModelAttribute ProductDto productDto) {
        Order order = this.registerOrder();
        Product product = this.createProduct(productDto);
        int quantity = productDto.getQuantity();
        this.addLineItem(order, quantity , product);
        return "redirect:/shop";
    }

    private Order registerOrder() {
        Location location = this.locationService.create();
        Order order = this.orderService.create(location, new Date());
        return this.orderService.create(order);
    }

    private Product createProduct(ProductDto productDto) {
        Product product = this.productService.create(productDto);
        return product;
    }

    private void addLineItem(Order order, int quantity, Product product) {
        LineItem lineItem = this.lineItemService.create(order, quantity, product);
        order.addLineItem(lineItem);
        this.orderService.create(order);
    }
}

package com.onlineshop.serviceimpl;

import com.onlineshop.domain.model.LineItem;
import com.onlineshop.domain.model.Order;
import com.onlineshop.domain.model.Product;
import com.onlineshop.factory.LineItemFactory;
import com.onlineshop.service.LineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineItemImpl implements LineItemService {

    @Autowired
    private LineItemFactory lineItemFactory;

    @Override
    public LineItem create(Order order, int quantity, Product product) {
        LineItem lineItem = this.lineItemFactory.create(quantity, product, order);
        return lineItem;
    }
}

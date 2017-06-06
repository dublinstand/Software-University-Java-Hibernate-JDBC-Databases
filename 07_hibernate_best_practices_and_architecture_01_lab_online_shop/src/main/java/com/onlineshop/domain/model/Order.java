package com.onlineshop.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_date")
    private Date orderDate;

    @OneToMany(mappedBy = "order", targetEntity = LineItem.class, cascade = CascadeType.ALL)
    private List<LineItem> lineItems;

    //using @Transient the data won't be persisted in the database
    @Transient
    private Location location;

    public Order() {
        this.setLineItems(new ArrayList<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void addLineItem (LineItem lineItem){
        this.getLineItems().add(lineItem);
    }

    public void removeLineItem (LineItem lineItem){
        this.getLineItems().remove(lineItem);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

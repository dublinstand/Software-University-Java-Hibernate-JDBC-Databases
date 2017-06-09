package com.airconditioners.domain.entities.airConditioners;

import com.airconditioners.domain.entities.reports.Report;

import javax.persistence.*;

@Entity
@Table(name = "basic_air_conditioners")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BasicAirConditioner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private String manufacturer;

    @Basic
    private String model;

    @OneToOne(mappedBy = "basicAirConditioner")
    private Report report;

    public BasicAirConditioner() {
    }

    public BasicAirConditioner(String manufacturer, String model) {
        this();
        this.setManufacturer(manufacturer);
        this.setModel(model);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        if(manufacturer.length() < 4){
            throw new IllegalArgumentException("Manufacturer's name must be at least 4 symbols long.");
        }

        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        if(model.length() < 2){
            throw new IllegalArgumentException("Model's name must be at least 2 symbols long.");
        }
        this.model = model;
    }

    public abstract boolean isEfficient();

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Model: " + this.getModel()).append(System.lineSeparator());
        stringBuilder.append("Manufacturer: " + this.getManufacturer());
        return stringBuilder.toString();
    }
}

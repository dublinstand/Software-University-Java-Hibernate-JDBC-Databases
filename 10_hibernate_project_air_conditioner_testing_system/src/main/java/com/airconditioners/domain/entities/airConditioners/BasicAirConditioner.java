package com.airconditioners.domain.entities.airConditioners;

import com.airconditioners.domain.entities.reports.Report;

import javax.persistence.*;

//we'll store all data in one table from the different conditioners
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

    //we need to call this() for the reports - another entity
    public BasicAirConditioner(String manufacturer, String model) {
        this();
        this.setManufacturer(manufacturer);
        this.setModel(model);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        if(manufacturer.length() < 4){
            throw new IllegalArgumentException("Manufacturer's name must be at least 4 symbols long.");
        }

        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model.length() < 2) {
            throw new IllegalArgumentException("Models's length must be at least 4 symbols long.");
        }

        this.model = model;
    }

    //we want the classes that extend BasicAirConditioner to override this method because they will have different methods
    //to check if it is efficient
    protected abstract boolean isEfficient();
}

package com.onlineshop.domain.model;

import java.util.Random;

public final class Location {

    private static final Random random = new Random();

    private float latitude;

    private float longitude;

    //here we create a random location that will be used in the Order class and will not be saved to the database
    //using @Transient
    public Location() {
        this.setLatitude(Location.random.nextFloat());
        this.setLongitude(Location.random.nextFloat());
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}

package com.onlineshop.serviceimpl;

import com.onlineshop.domain.model.Location;
import com.onlineshop.factory.LocationFactory;
import com.onlineshop.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServcieImpl implements LocationService {

    @Autowired
    private LocationFactory locationFactory;

    @Override
    public Location create() {
        return this.locationFactory.create();
    }
}

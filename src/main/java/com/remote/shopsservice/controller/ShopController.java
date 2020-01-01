package com.remote.shopsservice.controller;

import com.remote.shopsservice.model.Location;
import com.remote.shopsservice.model.Shop;
import com.remote.shopsservice.service.LocationService;
import com.remote.shopsservice.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/v1/shops")
public class ShopController {

    @Autowired
    private ShopService service;

    @Autowired
    private LocationService locationService;

    @PostMapping(value = "/nearby")
    public ResponseEntity<List<Shop>> getNearbyShops(@RequestBody Location location) {
        List<Shop> shops = service.findAll();
        Collections.sort(shops, new Comparator<Shop>() {
            @Override
            public int compare(Shop shop1, Shop shop2) {
                Double distance1 = locationService.distanceTo(location, shop1.getLocation());
                Double distance2 = locationService.distanceTo(location, shop2.getLocation());
                return distance1.compareTo(distance2);
            }
        });
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }

}

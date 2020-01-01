package com.remote.shopsservice.controller;

import com.remote.shopsservice.model.Location;
import com.remote.shopsservice.model.Shop;
import com.remote.shopsservice.service.LocationService;
import com.remote.shopsservice.service.ShopService;
import com.remote.shopsservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserService userService;


    @PostMapping(value = "/nearby")
    public ResponseEntity<List<Shop>> getNearbyShops(@RequestBody(required = false) Location location) {
        List<Shop> shops = service.findAll(); //Todo check if shop is liked !!
        if (location != null) {
            Collections.sort(shops, new Comparator<Shop>() {
                @Override
                public int compare(Shop shop1, Shop shop2) {
                    Double distance1 = locationService.distanceTo(location, shop1.getLocation());
                    Double distance2 = locationService.distanceTo(location, shop2.getLocation());
                    return distance1.compareTo(distance2);
                }
            });
        }
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }

    @PostMapping(value = "/like")
    public ResponseEntity like(@RequestBody long id) {
        if(service.findById(id).isPresent()){
            try {
                service.like(id);
                return new ResponseEntity<>( HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/remove-like")
    public ResponseEntity removeLike(@RequestBody long id) {
        try {
            service.removeLike(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/preferred-shops")
    public ResponseEntity<List<Shop>> getLikedShops() {
        List<Shop> preferredShops = userService.getPreferredShops();
        return new ResponseEntity<>(preferredShops ,HttpStatus.OK);
    }

}

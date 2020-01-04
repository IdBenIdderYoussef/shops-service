package com.remote.shopsservice.controller;

import com.remote.shopsservice.model.Location;
import com.remote.shopsservice.model.Shop;
import com.remote.shopsservice.model.UserDislikedShop;
import com.remote.shopsservice.service.LocationService;
import com.remote.shopsservice.service.ShopService;
import com.remote.shopsservice.service.UserService;
import com.remote.shopsservice.service.UserDislikedShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/v1/shops")
public class ShopController {

    @Autowired
    private ShopService service;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/nearby")
    public ResponseEntity<List<Shop>> getNearbyShops(@RequestBody(required = false) Location location) {
        List<Shop> shops = service.getNearbyShops(location);
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

    @PostMapping(value = "/dislike")
    public ResponseEntity disLike(@RequestBody long id) {
        if(service.findById(id).isPresent()){
            try {
                service.disLike(id);
                return new ResponseEntity<>( HttpStatus.OK);
            }catch (Exception e){
                System.out.println(e.getMessage());
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

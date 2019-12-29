package com.remote.shopsservice.controller;

import com.remote.shopsservice.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/shops")
public class ShopController {

    @Autowired
    private ShopService service;

}

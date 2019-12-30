package com.remote.shopsservice;

import com.remote.shopsservice.model.Location;
import com.remote.shopsservice.model.Shop;
import com.remote.shopsservice.model.User;
import com.remote.shopsservice.service.ShopService;
import com.remote.shopsservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final ShopService shopService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserService userService, ShopService shopService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.shopService = shopService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Shop shop = Shop.builder().name("marrakesh").location(Location.builder().latitude(31.669746).longitude(-7.973328).build()).build();
        Shop shop1 = Shop.builder().name("paris").location(Location.builder().latitude(48.864716).longitude(2.349014).build()).build();
        Shop shop2 = Shop.builder().name("new york").location(Location.builder().latitude(40.730610).longitude(-73.935242).build()).build();
        Shop shop3 = Shop.builder().name("dubai").location(Location.builder().latitude(25.276987).longitude(55.296249).build()).build();
        Shop shop4 = Shop.builder().name("beijing").location(Location.builder().latitude(39.916668).longitude(116.383331).build()).build();
        shopService.create(shop);
        shopService.create(shop1);
        shopService.create(shop3);
        shopService.create(shop2);
        shopService.create(shop4);
    }
}

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
        Shop shop = Shop.builder().name("Magic Shop").image("https://res-console.cloudinary.com/dhvkitjme/thumbnails/v1/image/upload/v1577877005/c2hvcHMvNUFfeTRkYWRr/preview")
                .location(Location.builder().latitude(31.669746).longitude(-7.973328).build()).build();
        Shop shop1 = Shop.builder().name("Kei Electrical").image("https://res-console.cloudinary.com/dhvkitjme/thumbnails/v1/image/upload/v1577876984/c2hvcHMvZGVlcC1lbGVjdHJpY2Fscy1hZGFqYW4tZG4tc3VyYXQtZWxlY3RyaWNhbC1zaG9wcy1ndHlsb19qeXR0c3E=/preview")
                .location(Location.builder().latitude(48.864716).longitude(2.349014).build()).build();
        Shop shop2 = Shop.builder().name("Love me again").image("https://res-console.cloudinary.com/dhvkitjme/thumbnails/v1/image/upload/v1577876983/c2hvcHMvbmV3LXNob3Bfb3d3enNw/preview")
                .location(Location.builder().latitude(40.730610).longitude(-73.935242).build()).build();
        Shop shop3 = Shop.builder().name("Shop Store ").image("https://res-console.cloudinary.com/dhvkitjme/thumbnails/v1/image/upload/v1577876979/c2hvcHMvc2hvcF9waHdjMHA=/preview")
                .location(Location.builder().latitude(25.276987).longitude(55.296249).build()).build();
        Shop shop4 = Shop.builder().name("interSport").image("https://res-console.cloudinary.com/dhvkitjme/thumbnails/v1/image/upload/v1577876964/c2hvcHMvc3BvcnRfZHIxYWE0/preview")
                .location(Location.builder().latitude(39.916668).longitude(116.383331).build()).build();
        shopService.create(shop);
        shopService.create(shop1);
        shopService.create(shop3);
        shopService.create(shop2);
        shopService.create(shop4);
    }
}

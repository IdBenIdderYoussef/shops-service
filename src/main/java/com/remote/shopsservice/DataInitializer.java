package com.remote.shopsservice;

import com.remote.shopsservice.model.Location;
import com.remote.shopsservice.model.Shop;
import com.remote.shopsservice.model.User;
import com.remote.shopsservice.service.ShopService;
import com.remote.shopsservice.service.UserDislikedShopService;
import com.remote.shopsservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final ShopService shopService;
    private final UserDislikedShopService userDislikedShopService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserService userService, ShopService shopService, UserDislikedShopService userDislikedShopService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.shopService = shopService;
        this.userDislikedShopService = userDislikedShopService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = User.builder().username("test@gmail.com").password(passwordEncoder.encode("secret")).build();
        userService.create(user);

        Shop shop = Shop.builder().name("Magic Shop").image("https://res.cloudinary.com/dhvkitjme/image/upload/v1577877005/shops/5A_y4dadk.jpg")
                .location(Location.builder().latitude(31.669746).longitude(-7.973328).build()).build();
        Shop shop1 = Shop.builder().name("Kei Electrical").image("https://res.cloudinary.com/dhvkitjme/image/upload/v1577876984/shops/deep-electricals-adajan-dn-surat-electrical-shops-gtylo_jyttsq.jpg")
                .location(Location.builder().latitude(48.864716).longitude(2.349014).build()).build();
        Shop shop2 = Shop.builder().name("Love me again").image("https://res.cloudinary.com/dhvkitjme/image/upload/v1577876983/shops/new-shop_owwzsp.jpg")
                .location(Location.builder().latitude(40.730610).longitude(-73.935242).build()).build();
        Shop shop3 = Shop.builder().name("Shop Store ").image("https://res.cloudinary.com/dhvkitjme/image/upload/v1577876941/shops/Aelia-landside-2_aes11d.jpg")
                .location(Location.builder().latitude(25.276987).longitude(55.296249).build()).build();
        Shop shop4 = Shop.builder().name("interSport").image("https://res.cloudinary.com/dhvkitjme/image/upload/v1577876964/shops/sport_dr1aa4.jpg")
                .location(Location.builder().latitude(39.916668).longitude(116.383331).build()).build();
        shopService.create(shop);
        shopService.create(shop4);
        shopService.create(shop2);
        shopService.create(shop3);
        shopService.create(shop1);

    }
}

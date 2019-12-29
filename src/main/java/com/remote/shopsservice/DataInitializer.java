package com.remote.shopsservice;

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

    }
}

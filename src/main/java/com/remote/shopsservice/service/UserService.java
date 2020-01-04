package com.remote.shopsservice.service;

import com.remote.shopsservice.model.Shop;
import com.remote.shopsservice.model.User;
import com.remote.shopsservice.model.UserDislikedShop;
import com.remote.shopsservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AbstractService<Long, UserRepository, User> {

    @Autowired
    private UserDislikedShopService userDislikedShopService;

    public UserService(UserRepository repository) {
        super(repository);
    }

    // get connected user
    public User getCurrentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User user = repository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return user;
    }

    public List<Shop> getPreferredShops(){
        return getCurrentUser().getPreferredShops();
    }

}

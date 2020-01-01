package com.remote.shopsservice.service;

import com.remote.shopsservice.model.Shop;
import com.remote.shopsservice.model.User;
import com.remote.shopsservice.repository.ShopRepository;
import com.remote.shopsservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ShopService extends AbstractService<Long, ShopRepository, Shop> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public ShopService(ShopRepository repository) {
        super(repository);
    }

    public void like(long id){
        Shop shop = this.findById(id).get();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        user.getPreferredShops().add(shop);
        userService.update(user);
    }

    public void removeLike(long id){
        Shop shop = this.findById(id).get();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        user.getPreferredShops().remove(shop);
        userService.update(user);
    }

}

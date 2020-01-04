package com.remote.shopsservice.service;

import com.remote.shopsservice.model.Shop;
import com.remote.shopsservice.model.User;
import com.remote.shopsservice.model.UserDislikedShop;
import com.remote.shopsservice.model.UserDislikedShopId;
import com.remote.shopsservice.repository.UserDislikedShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDislikedShopService extends AbstractService<UserDislikedShopId, UserDislikedShopRepository, UserDislikedShop> {
    @Autowired
    private UserService userService;

    public UserDislikedShopService(UserDislikedShopRepository repository) {
        super(repository);
    }

    public List<UserDislikedShop> getUserDislikedShop(User user) {
        return repository.findByUser(user);
    }

    public UserDislikedShop findByUserAndShop(User user, Shop shop) {
        return repository.findByUserAndShop(user, shop);
    }

}

package com.remote.shopsservice.repository;

import com.remote.shopsservice.model.Shop;
import com.remote.shopsservice.model.User;
import com.remote.shopsservice.model.UserDislikedShop;
import com.remote.shopsservice.model.UserDislikedShopId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDislikedShopRepository extends JpaRepository<UserDislikedShop, UserDislikedShopId> {
    List<UserDislikedShop> findByUser(User user);
    UserDislikedShop findByUserAndShop(User user, Shop shop);

}

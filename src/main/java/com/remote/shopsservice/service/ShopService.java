package com.remote.shopsservice.service;

import com.remote.shopsservice.model.Location;
import com.remote.shopsservice.model.Shop;
import com.remote.shopsservice.model.User;
import com.remote.shopsservice.model.UserDislikedShop;
import com.remote.shopsservice.repository.ShopRepository;
import com.remote.shopsservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ShopService extends AbstractService<Long, ShopRepository, Shop> {

    @Autowired
    private ShopService service;

    @Autowired
    private LocationService locationService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDislikedShopService userDislikedShopService;

    public ShopService(ShopRepository repository) {
        super(repository);
    }


    public List<Shop> getNearbyShops(Location location){
        //get all shops
        List<Shop> allShops = service.findAll();

        //get preferred shops
        List<Shop> preferredShops = userService.getPreferredShops();


        List<Shop> shops= new ArrayList<Shop>();

        // filter shops based on there are preferred or disliked within last two hours
        for(Shop shop : allShops){
            if(!preferredShops.contains(shop)){

                User user = userService.getCurrentUser();
                UserDislikedShop userDislikedShop = userDislikedShopService.findByUserAndShop(user,shop);
                if(userDislikedShop != null){
                    long diffInMillie = Math.abs((new Date()).getTime() - userDislikedShop.getLastModifiedDate().getTime());
                    long diff = TimeUnit.HOURS.convert(diffInMillie, TimeUnit.MILLISECONDS);
                    if(diff >= 2){
                        shops.add(shop);
                    }
                }else {
                    shops.add(shop);
                }
            }
        }

        // sort shops bases on location
        if (location != null) {
            Collections.sort(shops, new Comparator<Shop>() {
                @Override
                public int compare(Shop shop1, Shop shop2) {
                    Double distance1 = locationService.distanceTo(location, shop1.getLocation());
                    Double distance2 = locationService.distanceTo(location, shop2.getLocation());
                    return distance1.compareTo(distance2);
                }
            });
        }
        return shops;
    }

    public void like(long id){
        Shop shop = this.findById(id).get();
        User user = userService.getCurrentUser();
        user.getPreferredShops().add(shop);
        userService.update(user);
    }

    public void removeLike(long id){
        Shop shop = this.findById(id).get();
        User user = userService.getCurrentUser();
        user.getPreferredShops().remove(shop);
        userService.update(user);
    }

    public void disLike(long id){
        Shop shop = this.findById(id).get();
        User user = userService.getCurrentUser();
        UserDislikedShop dislikedShop = userDislikedShopService.findByUserAndShop(user,shop);
        if(dislikedShop == null){
            UserDislikedShop userDislikedShop = UserDislikedShop.builder().user(user).shop(shop).build();
            userDislikedShopService.create(userDislikedShop);
        }else {
            dislikedShop.setLastModifiedDate(new Date());
            userDislikedShopService.update(dislikedShop);
        }
    }

}

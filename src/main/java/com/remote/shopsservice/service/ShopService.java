package com.remote.shopsservice.service;

import com.remote.shopsservice.model.Shop;
import com.remote.shopsservice.repository.ShopRepository;
import org.springframework.stereotype.Service;

@Service
public class ShopService extends AbstractService<Long, ShopRepository, Shop> {
    public ShopService(ShopRepository repository) {
        super(repository);
    }
}

package com.remote.shopsservice.service;

import com.remote.shopsservice.model.User;
import com.remote.shopsservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<Long, UserRepository, User> {
    public UserService(UserRepository repository) {
        super(repository);
    }
}

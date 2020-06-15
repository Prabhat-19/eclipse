package com.payearly.serviceImpl;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.payearly.domain.User;
import com.payearly.repository.UserRepository;
import com.payearly.service.UserService;

@Service
public class UserServiceImpl  implements UserService {

    private final UserRepository userRepositor;
     
    public UserServiceImpl(UserRepository userRepositor) {
        super();
        this.userRepositor = userRepositor;
    }

    @Override
    public User createUser(@Valid User user) {
        // TODO Auto-generated method stub
        return userRepositor.save(user);
    }

    @Override
    public  User updateUser(@Valid User user) {
        // TODO Auto-generated method stub
        return userRepositor.save(user);
    }
    
    @Override
    public Page<User> getAllManagedUsers(Pageable pageable) {
        // TODO Auto-generated method stub
        return userRepositor.findAll(pageable);
    }
    
    

}

package com.payearly.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.payearly.domain.User;

public interface UserService {

    User createUser(@Valid User user);

    User updateUser(@Valid User user);

    Page<User> getAllManagedUsers(Pageable pageable);

}

package com.payearly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payearly.domain.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

    Optional<User> findOneByLogin(String lowerCase);

    Optional<User> findOneByEmailIgnoreCase(String email);

}

package com.example.silkpay.repository;

import com.example.silkpay.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findAllByActiveIsTrue();


    Optional<User> findByUsernameAndActiveIsTrue(String username);

}

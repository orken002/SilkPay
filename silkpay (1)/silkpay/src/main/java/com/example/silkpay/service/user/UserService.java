package com.example.silkpay.service.user;

import com.example.silkpay.model.entity.User;
import com.example.silkpay.model.request.UserRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {

    List<User> findAllUsers();
    User findUserByUsername(String username);
    UUID createUser(UserRequest userRequest);
    UUID updateUser(Map<String,String> userParams);
    void deleteUser(String username);

}

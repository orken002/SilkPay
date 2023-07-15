package com.example.silkpay.controller;

import com.example.silkpay.mapper.UserMapper;
import com.example.silkpay.model.entity.User;
import com.example.silkpay.model.request.UserRequest;
import com.example.silkpay.model.response.UserResponse;
import com.example.silkpay.service.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(UserMapper.toDto(users));
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(name = "username") String username){
        User user = userService.findUserByUsername(username);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @PostMapping(path = "/")
    public ResponseEntity<UUID> createUser(@Valid @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @PutMapping(path = "/")
    public ResponseEntity<UUID> updateUser(@RequestBody Map<String,String> params){
        return ResponseEntity.ok(userService.updateUser(params));
    }

    @DeleteMapping(path = "/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "username") String username){
        userService.deleteUser(username);
        return ResponseEntity.ok().build();

    }

}

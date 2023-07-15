package com.example.silkpay.service.user;

import com.example.silkpay.mapper.UserMapper;
import com.example.silkpay.model.entity.User;
import com.example.silkpay.model.enums.ErrorCode;
import com.example.silkpay.model.exeptions.ServiceException;
import com.example.silkpay.model.request.UserRequest;
import com.example.silkpay.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

//    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllByActiveIsTrue();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsernameAndActiveIsTrue(username)
                .orElseThrow(()-> new ServiceException(
                        HttpStatus.NOT_FOUND,
                        ErrorCode.NOT_FOUND,
                        String.format("user with username: %s doesn't exit", username)));
    }

    @Override
    @Transactional
    public UUID createUser(UserRequest userRequest) {
        Optional<User> userOpt = userRepository.findByUsernameAndActiveIsTrue(userRequest.getUsername());
        userOpt.ifPresent(it -> {
            throw new ServiceException(
                    HttpStatus.BAD_REQUEST,
                    ErrorCode.ALREADY_EXISTS,
                    String.format("user with username: %s is already exist", userRequest.getUsername()));
        });

        User user = UserMapper.fromDto(userRequest);
//        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setPassword(userRequest.getPassword());
        return userRepository.save(user).getId();
    }

    @Override
    @Transactional
    public UUID updateUser(Map<String, String> userParams) {
        return null;
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsernameAndActiveIsTrue(username)
                .orElseThrow(()-> new ServiceException(
                                HttpStatus.NOT_FOUND,
                                ErrorCode.NOT_FOUND,
                                String.format("user with username: %s doesn't exit", username)));
        user.setActive(false);
        user.setDeletedAt(new Date());
        userRepository.save(user);
    }
}

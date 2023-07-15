package com.example.silkpay.mapper;

import com.example.silkpay.model.entity.Card;
import com.example.silkpay.model.entity.User;
import com.example.silkpay.model.request.UserRequest;
import com.example.silkpay.model.response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static List<User> fromDto(List<UserRequest> userRequest){
        return userRequest.stream().map(UserMapper::fromDto).collect(Collectors.toList());
    }

    public static User fromDto(UserRequest userRequest){
        return User.builder()
                .username(userRequest.getUsername())
                .surname(userRequest.getSurname())
                .name(userRequest.getName())
                .patronymic(userRequest.getPatronymic())
                .active(true)
                .build();
    }

    public static List<UserResponse> toDto(List<User> userRequest){
        return userRequest.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    public static UserResponse toDto(User user){
        return UserResponse.builder()
                .surname(user.getSurname())
                .name(user.getName())
                .patronymic(user.getPatronymic())
                .cards(CardMapper.toDto(user.getCards()))
                .build();
    }

}

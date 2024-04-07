package com.news.news.service;

import com.news.news.dto.UserDTO;
import com.news.news.model.User;

public class UserService implements IUserService {
    @Override
    public User creatUser(UserDTO userDTO) {
        User newUser = User.builder().fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .address(userDTO.getAddress())
                .password(userDTO.getPassword())
                .dateOfBirth(userDTO.getDateOfBirth())
                .facebookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .build();
        return null;
    }

    @Override
    public User login(String numberPhone, String password) {
        return null;
    }
}

package com.news.news.service;

import com.news.news.dto.UserDTO;
import com.news.news.model.User;

public interface IUserService {
    User creatUser(UserDTO userDTO);
    User login(String numberPhone,String password);
}

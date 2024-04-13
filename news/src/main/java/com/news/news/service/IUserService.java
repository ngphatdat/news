package com.news.news.service;

import com.news.news.dto.UserDTO;
import com.news.news.exception.DataNotFoundException;
import com.news.news.model.User;

public interface IUserService {
    User creatUser(UserDTO userDTO) throws DataNotFoundException;

    String login(String numberPhone, String password) throws DataNotFoundException;
}

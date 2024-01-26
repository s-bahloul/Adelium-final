package com.adelium.web.service;


import com.adelium.web.entity.User;
import org.springframework.stereotype.Service;
import com.adelium.web.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByUsername(String username);

    List<UserDto> findAllUsers();
}
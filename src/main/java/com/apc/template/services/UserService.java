package com.apc.template.services;

import com.apc.template.model.User;
import com.apc.template.commons.dto.UserDTO;

import java.util.List;

public interface UserService {


    List<User> findAll();

    User saveOrUpdate(UserDTO user);
}

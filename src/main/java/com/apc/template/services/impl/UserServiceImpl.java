package com.apc.template.services.impl;

import com.apc.template.model.User;
import com.apc.template.repository.UserRepository;
import com.apc.template.services.UserService;
import com.apc.template.commons.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        List<User> users = null;
        log.info("========= Fetching all users");
        try {
            users = userRepository.findAll();
            log.info("========== All users fetched successfully. Size : {}", users);
        } catch (Exception e) {
            log.error("Exception occurred while fetching all users. Message: {}", e.getMessage());
        }
        return users;
    }

    @Override
    public User saveOrUpdate(UserDTO user) {
        User newUser = new User();
        log.info("========= Saving all users");
        try {
            User u = new User();
            u.setUsername(user.getUsername());
            u.setAddress1(user.getAddress1());
            u.setAddress2(user.getAddress2());
            newUser = userRepository.save(u);
            log.info("========== User {} successfully save. ID : {}",u.getUsername(), u.getId());
        } catch (Exception e) {
            log.error("Exception occurred while saving the user. Message: {}", e.getMessage());
        }

        return newUser;
    }
}

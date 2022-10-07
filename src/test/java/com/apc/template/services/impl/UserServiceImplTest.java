package com.apc.template.services.impl;

import com.apc.template.model.User;
import com.apc.template.repository.UserRepository;
import com.apc.template.commons.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(userRepository.findAll()).thenReturn(mockUsers());
        when(userRepository.save(getMockUser())).thenReturn(getMockUser());
    }

    @Test
    void success_findAll() {
        when(userServiceImpl.findAll()).thenReturn(mockUsers());
        List<User> users = userRepository.findAll();
        assertThat(users).isEqualTo(mockUsers());
    }

    @Test
    void failed_findAll() {
        when(userRepository.findAll()).thenThrow(new RuntimeException());
        when(userServiceImpl.findAll()).thenReturn(mockUsers());
    }

    @Test
    void success_saveOrUpdate() {
        when(userServiceImpl.saveOrUpdate(UserDTO.builder()
                .username("test-username").address1("address1").address2("address2")
                .build())).thenReturn(getMockUser());
        assertThat(userRepository.save(getMockUser())).isNotEqualTo(new User());
    }

    @Test
    void failed_saveOrUpdate() {
        try {
            when(userServiceImpl.saveOrUpdate(any())).thenThrow(new IllegalStateException());
            when(userServiceImpl.saveOrUpdate(UserDTO.builder()
                    .username("test-username").address1("address1").address2("address2")
                    .build())).thenReturn(getMockUser());
        } catch (Exception ignored) {}

    }


    private List<User> mockUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i > 5; i++)
            users.add(getMockUser());

        return users;
    }

    private User getMockUser() {
        Random random = new Random();
        User u = new User();
        u.setUsername("test-user");
        u.setAddress1("test-address1");
        u.setAddress2("test-address2");
        u.setId(Long.parseLong(String.valueOf(random.nextInt(999999) + 100)));
        u.setInactive(false);
        u.setTimestamp(new Date());
        return u;
    }
}
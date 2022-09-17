package com.fawry.auditing_v1;

import com.fawry.auditing_v1.models.User;
import com.fawry.auditing_v1.repositories.UserRepository;
import com.fawry.auditing_v1.sevices.UserService;
import com.fawry.auditing_v1.sevices.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    private static final Long USER_ID = 1l;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void whenSeachByUserId_CallFindByIdMethod(){
        userService.findUserById(any());
        verify(userRepository,times(1)).findById(any());
    }

    @Test
    public void whenSearchByUserId_returnTheSpecificUser(){
        User user = new User();
        user.setId(USER_ID);
        user.setValue("ahmed");

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        assertThat(userService.findUserById(USER_ID).get().getId()).isEqualTo(USER_ID);
    }

}

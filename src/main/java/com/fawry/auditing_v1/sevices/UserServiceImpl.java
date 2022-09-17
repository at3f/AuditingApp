package com.fawry.auditing_v1.sevices;

import com.fawry.auditing_v1.models.User;
import com.fawry.auditing_v1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findUserById(Long id){
         return userRepository.findById(id);
    }
}

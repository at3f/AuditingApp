package com.fawry.auditing_v1.sevices;

import com.fawry.auditing_v1.models.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);
}

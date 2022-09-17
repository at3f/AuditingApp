package com.fawry.auditing_v1.repositories;

import com.fawry.auditing_v1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

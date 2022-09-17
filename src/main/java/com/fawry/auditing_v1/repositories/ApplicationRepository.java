package com.fawry.auditing_v1.repositories;

import com.fawry.auditing_v1.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
}

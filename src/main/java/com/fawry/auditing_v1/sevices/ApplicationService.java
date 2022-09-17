package com.fawry.auditing_v1.sevices;

import com.fawry.auditing_v1.models.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    Optional<Application> findApplicationById(Long id);
    List<Application> findApplications();
}

package com.fawry.auditing_v1.sevices;

import com.fawry.auditing_v1.models.Be;

import java.util.Optional;

public interface BeService {
    Optional<Be> findBeById(Long id);
}

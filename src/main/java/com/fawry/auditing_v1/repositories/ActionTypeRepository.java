package com.fawry.auditing_v1.repositories;

import com.fawry.auditing_v1.models.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActionTypeRepository extends JpaRepository<ActionType,Long> {
    Optional<ActionType> getActionTypeByCode(String code);
}

package com.fawry.auditing_v1.repositories;

import com.fawry.auditing_v1.models.ParamType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParamTypeRepository extends JpaRepository<ParamType,Long> {
    Optional<ParamType> getParamTypeByCode(String code);
}

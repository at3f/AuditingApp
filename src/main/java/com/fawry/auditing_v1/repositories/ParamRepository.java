package com.fawry.auditing_v1.repositories;

import com.fawry.auditing_v1.models.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamRepository extends JpaRepository<Param,Long> {
}

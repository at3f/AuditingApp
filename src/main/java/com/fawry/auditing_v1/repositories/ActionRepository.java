package com.fawry.auditing_v1.repositories;

import com.fawry.auditing_v1.models.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<Action,Long> {
}

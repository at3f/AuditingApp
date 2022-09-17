package com.fawry.auditing_v1.repositories;

import com.fawry.auditing_v1.models.Action;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ActionCustomRepository {
    List<Action> findActions(Map<String,String> args);
}

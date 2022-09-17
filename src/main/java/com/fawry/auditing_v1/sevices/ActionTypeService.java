package com.fawry.auditing_v1.sevices;

import com.fawry.auditing_v1.models.ActionType;

import java.util.List;
import java.util.Optional;

public interface ActionTypeService {
    Optional<ActionType> findActionType(String code);
    List<ActionType> findActionTypes();
}

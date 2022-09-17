package com.fawry.auditing_v1.sevices;

import com.fawry.auditing_v1.dtos.ActionDto;
import com.fawry.auditing_v1.models.Action;

import java.util.List;
import java.util.Map;

public interface ActionService {
    Action AddAction(ActionDto actionDto);
    Action findActionById(Long id);

    List<Action> findActions(Map<String, String> args);
}

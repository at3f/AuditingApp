package com.fawry.auditing_v1.sevices;

import com.fawry.auditing_v1.models.ActionType;
import com.fawry.auditing_v1.repositories.ActionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActionTypeServiceImpl implements ActionTypeService {
    @Autowired
    private ActionTypeRepository actionTypeRepository;

    public Optional<ActionType> findActionType(String code){
        return actionTypeRepository.getActionTypeByCode(code);
    }

    @Override
    public List<ActionType> findActionTypes() {
        return actionTypeRepository.findAll();
    }
}

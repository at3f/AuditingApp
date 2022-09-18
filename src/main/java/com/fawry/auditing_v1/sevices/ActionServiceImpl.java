package com.fawry.auditing_v1.sevices;

import com.fawry.auditing_v1.dtos.ActionDto;
import com.fawry.auditing_v1.dtos.ParamDto;
import com.fawry.auditing_v1.helpers.Validator;
import com.fawry.auditing_v1.helpers.ActionDescription;
import com.fawry.auditing_v1.mappers.ActionMapper;
import com.fawry.auditing_v1.mappers.ActionMapperImpl;
import com.fawry.auditing_v1.models.Action;
import com.fawry.auditing_v1.models.User;
import com.fawry.auditing_v1.repositories.ActionRepository;
import com.fawry.auditing_v1.repositories.ActionCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ActionServiceImpl implements ActionService {
    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ActionTypeService actionTypeService;
    @Autowired
    private UserService userService;
    @Autowired
    private BeService beService;
    @Autowired
    private Validator validator;
    @Autowired
    private ActionDescription actionDescription;
    @Autowired
    private ActionMapper actionMapper;
    @Override
    public Action AddAction(ActionDto actionDto){
        validator.validateActionRequestBody(actionDto);

        User user = userService.findUserById(actionDto.getUserId()).get();

        ParamDto userParamDto = new ParamDto(user.getId(),null, user.getValue(), "user");
        actionDto.getParamDtos().add(userParamDto);

        Action action = actionMapper.mapToAction(actionDto,applicationService,userService,actionTypeService,beService,actionDescription);
        actionDto.getParamDtos().removeIf(c->c.getName()=="user");

        return actionRepository.saveAndFlush(action);
    }
    @Override
    public Action findActionById(Long id){
        return actionRepository.getReferenceById(id);
    }

    //==========================QueryDsl==========================
    @Autowired
    private ActionCustomRepository actionCustomRepository;
    @Override
    public List<Action> findActions(Map<String, String> args){
        return actionCustomRepository.findActions(args);
    }

}

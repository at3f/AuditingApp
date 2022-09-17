package com.fawry.auditing_v1.helpers;

import com.fawry.auditing_v1.dtos.ActionDto;
import com.fawry.auditing_v1.dtos.ParamDto;
import com.fawry.auditing_v1.exceptions.*;
import com.fawry.auditing_v1.sevices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Component
public class Validator {
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ActionTypeService actionTypeService;
    @Autowired
    private UserService userService;
    @Autowired
    private BeService beService;
    @Autowired
    private ParamTypeService paramTypeService;
    public boolean validateActionRequestBody(ActionDto actionDto){
         userService.findUserById(actionDto.getUserId()).orElseThrow(()->new UserNotFoundException("User Not Found"));
         applicationService.findApplicationById(actionDto.getApplicationId()).orElseThrow(()->new ApplicationNotFoundException("Application Not Found"));
         actionTypeService.findActionType(actionDto.getActionTypeCode()).orElseThrow(()->new ActionTypeNotFoundException("ActionType Not Found"));
         beService.findBeById(actionDto.getBeId()).orElseThrow(()->new BeNotFoundException("Business Not Found"));
         actionDto.getParamDtos().forEach(x->{
             paramTypeService.findParamTypeByCode(x.getName()).orElseThrow(() -> new ParamTypeNotFoundException("ParamType Not Found"));;
         });
         return true;
    }
}

package com.fawry.auditing_v1.mappers;

import com.fawry.auditing_v1.dtos.ActionDto;
import com.fawry.auditing_v1.helpers.ActionDescription;
import com.fawry.auditing_v1.models.Action;
import com.fawry.auditing_v1.sevices.ActionTypeService;
import com.fawry.auditing_v1.sevices.ApplicationService;
import com.fawry.auditing_v1.sevices.BeService;
import com.fawry.auditing_v1.sevices.UserService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ActionMapper {
    ActionDto mapToActionDto(Action t);
    @Mappings({
            @Mapping(target = "application", expression = "java(applicationService.findApplicationById(t.getApplicationId()).get())"),
            @Mapping(target = "user", expression = "java(userService.findUserById(t.getUserId()).get())"),
            @Mapping(target = "actionType", expression = "java(actionTypeService.findActionType(t.getActionTypeCode()).get())"),
            @Mapping(target = "be", expression = "java(beService.findBeById(t.getBeId()).get())"),
            @Mapping(target = "description_en", expression = "java(actionDescription.createDescriptionFromTamplate(action.getActionType().getMessage_template_en(),t.getParamDtos()))"),
            @Mapping(target = "description_ar", expression = "java(actionDescription.createDescriptionFromTamplate(action.getActionType().getMessage_template_ar(),t.getParamDtos()))")
    })
    Action mapToAction(ActionDto t, @Context ApplicationService applicationService, @Context UserService userService, @Context ActionTypeService actionTypeService, @Context BeService beService,@Context ActionDescription actionDescription);
}

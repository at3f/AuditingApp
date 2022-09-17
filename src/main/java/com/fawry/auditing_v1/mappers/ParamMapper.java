package com.fawry.auditing_v1.mappers;

import com.fawry.auditing_v1.dtos.ParamDto;
import com.fawry.auditing_v1.models.Action;
import com.fawry.auditing_v1.models.Param;
import com.fawry.auditing_v1.sevices.ActionService;
import com.fawry.auditing_v1.sevices.ParamTypeService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface ParamMapper {
    ParamDto mapToParamDto(Param t);

    @Mappings({
            @Mapping(target = "action", expression = "java(actionService.findActionById(action.getId()))"),
            @Mapping(target = "paramType", expression = "java(paramTypeService.findParamTypeByCode(t.getName()).get())")
    })
    Param mapToParam(ParamDto t, @Context Action action, @Context ActionService actionService, @Context ParamTypeService paramTypeService);
    @Mappings({
            @Mapping(target = "action", expression = "java(actionService.findActionById(action.getId()))"),
            @Mapping(target = "paramType", expression = "java(paramTypeService.findParamTypeByCode(t.getName()).get())"),
    })
    List<Param> mapToParams(List<ParamDto> t, @Context Action action, @Context ActionService actionService, @Context ParamTypeService paramTypeService);
}

package com.fawry.auditing_v1.sevices;

import com.fawry.auditing_v1.dtos.ParamDto;
import com.fawry.auditing_v1.models.Action;
import com.fawry.auditing_v1.models.Param;

import java.util.List;

public interface ParamService {
    Param save(Param param);

    List<Param> saveAll(List<ParamDto> paramDtos, Action action);
}

package com.fawry.auditing_v1.sevices;

import com.fawry.auditing_v1.models.ParamType;

import java.util.List;
import java.util.Optional;

public interface ParamTypeService {
    Optional<ParamType> findParamTypeByCode(String code);
    List<ParamType> findParamTypes();
}

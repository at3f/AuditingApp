package com.fawry.auditing_v1.sevices;

import com.fawry.auditing_v1.models.ParamType;
import com.fawry.auditing_v1.repositories.ParamTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParamTypeServiceImpl implements ParamTypeService {
    @Autowired
    private ParamTypeRepository paramTypeRepository;
    @Override
    public Optional<ParamType> findParamTypeByCode(String code){
        return paramTypeRepository.getParamTypeByCode(code);
    }
    @Override
    public List<ParamType> findParamTypes() {
        return paramTypeRepository.findAll();
    }
}

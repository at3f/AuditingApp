package com.fawry.auditing_v1.sevices;

import com.fawry.auditing_v1.dtos.ParamDto;
import com.fawry.auditing_v1.mappers.ParamMapperImpl;
import com.fawry.auditing_v1.models.Action;
import com.fawry.auditing_v1.models.Param;
import com.fawry.auditing_v1.repositories.ParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParamServiceImpl implements ParamService {
    @Autowired
    private ParamRepository paramRepository;
    @Autowired
    private ParamTypeService paramTypeService;
    @Autowired
    private ActionService actionService;
    @Override
    public Param save(Param param){
        return paramRepository.saveAndFlush(param);
    }
    @Override
    public List<Param> saveAll(List<ParamDto> paramDtos, Action action){

        List<Param> params = new ParamMapperImpl().mapToParams(paramDtos,action,actionService,paramTypeService);

        return paramRepository.saveAllAndFlush(params);
    }

}

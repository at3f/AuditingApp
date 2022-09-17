package com.fawry.auditing_v1;

import com.fawry.auditing_v1.models.ParamType;
import com.fawry.auditing_v1.repositories.ParamTypeRepository;
import com.fawry.auditing_v1.sevices.ParamTypeService;
import com.fawry.auditing_v1.sevices.ParamTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ParamTypeServiceTest {

    private final String PARAM_TYPE_CODE = "order";

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ParamTypeService paramTypeService() {
            return new ParamTypeServiceImpl();
        }
    }

    @Autowired
    private ParamTypeService paramTypeService;
    @MockBean
    private ParamTypeRepository paramTypeRepository;

    @Test
    public void whenSeachByParamTypeCode_CallFindByIdMethod(){
        paramTypeService.findParamTypeByCode(PARAM_TYPE_CODE);
        verify(paramTypeRepository,times(1)).getParamTypeByCode(PARAM_TYPE_CODE);
    }

    @Test
    public void whenSearchByParamTypeCode_returnTheSpecificParamType(){
        ParamType paramType = new ParamType();
        paramType.setCode(PARAM_TYPE_CODE);

        when(paramTypeRepository.getParamTypeByCode(PARAM_TYPE_CODE)).thenReturn(Optional.of(paramType));

        assertThat(paramTypeService.findParamTypeByCode(PARAM_TYPE_CODE).get().getCode()).isEqualTo(PARAM_TYPE_CODE);
    }
}

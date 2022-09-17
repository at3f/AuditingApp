package com.fawry.auditing_v1;

import com.fawry.auditing_v1.dtos.ActionDto;
import com.fawry.auditing_v1.dtos.ParamDto;
import com.fawry.auditing_v1.exceptions.*;
import com.fawry.auditing_v1.repositories.ActionRepository;
import com.fawry.auditing_v1.sevices.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ActionServiceTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ActionService actionService() {
            return new ActionServiceImpl();
        }
    }


    @Autowired
    private ActionService actionService;
    @MockBean
    private ActionRepository actionRepository;
    private ActionDto actionDto;
    private List<ParamDto> params;
    private ParamDto paramDto;

    @BeforeEach
    void setUp() {

        actionDto = new ActionDto();
        actionDto.setId(1l);
        actionDto.setActionTypeCode("order_created");
        actionDto.setBeId(2l);
        actionDto.setApplicationId(3l);
        actionDto.setUserId(1l);

        params = new ArrayList<>();
        paramDto = new ParamDto();
        paramDto.setIdentifier("test");
        paramDto.setName("order");
        paramDto.setId(90l);
        paramDto.setValue("90");
        params.add(paramDto);
        actionDto.setParamDtos(params);
    }

    @Test
    public void whenAddActionWithValidData_ActionIsSaved(){
        actionService.AddAction(actionDto);

        verify(actionRepository,times(1)).saveAndFlush(any());
    }

    @Test
    public void whenAddActionWithInValidUserId_throwUserNotFoundException(){
        actionDto.setUserId(2l);

        assertThatThrownBy(() -> actionService.AddAction(actionDto) ).isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User Not Found");

        verify(actionRepository,never()).saveAndFlush(any());
    }

    @Test
    public void whenAddActionWithInValidActionTypeCode_throwActionTypeNotFoundException(){
        actionDto.setActionTypeCode("wrongCode");

        assertThatThrownBy(() -> actionService.AddAction(actionDto) ).isInstanceOf(ActionTypeNotFoundException.class)
                .hasMessageContaining("ActionType Not Found");

        verify(actionRepository,never()).saveAndFlush(any());
    }
    @Test
    public void whenAddActionWithInValidApplicationId_throwApplicationNotFoundException(){
        actionDto.setApplicationId(10l);

        assertThatThrownBy(() -> actionService.AddAction(actionDto) ).isInstanceOf(ApplicationNotFoundException.class)
                .hasMessageContaining("Application Not Found");

        verify(actionRepository,never()).saveAndFlush(any());
    }
    @Test
    public void whenAddActionWithInValidBusinessId_throwBeNotFoundException(){
        actionDto.setBeId(20l);

        assertThatThrownBy(() -> actionService.AddAction(actionDto) ).isInstanceOf(BeNotFoundException.class)
                .hasMessageContaining("Business Not Found");

        verify(actionRepository,never()).saveAndFlush(any());
    }
    @Test
    public void whenAddActionWithInValidParamName_throwParamTypeNotFoundException(){
        paramDto.setName("wroneName");
        params.add(paramDto);
        actionDto.setParamDtos(params);

        assertThatThrownBy(() -> actionService.AddAction(actionDto) ).isInstanceOf(ParamTypeNotFoundException.class)
                .hasMessageContaining("ParamType Not Found");

        verify(actionRepository,never()).saveAndFlush(any());
    }
}

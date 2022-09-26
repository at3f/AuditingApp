package com.fawry.auditing_v1.rabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fawry.auditing_v1.dtos.ActionDto;
import com.fawry.auditing_v1.models.Action;
import com.fawry.auditing_v1.sevices.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class Consumer {

    @Autowired
    private ActionService actionService;
    @Autowired
    private ParamService paramService;

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload ActionDto actionDto){
        Action actionSaved = actionService.AddAction(actionDto);
        paramService.saveAll(actionDto.getParamDtos(),actionSaved);
    }

}

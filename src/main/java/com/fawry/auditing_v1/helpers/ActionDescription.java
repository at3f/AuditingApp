package com.fawry.auditing_v1.helpers;

import com.fawry.auditing_v1.dtos.ParamDto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ActionDescription {
    public String createDescriptionFromTamplate(String template, List<ParamDto> paramDtos){
        for (ParamDto p: paramDtos) {
            template = template.replace("{{"+p.getName()+".value"+"}}",p.getValue());
        }
        return template;
    }
}

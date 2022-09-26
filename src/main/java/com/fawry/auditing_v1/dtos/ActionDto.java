package com.fawry.auditing_v1.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
@Data
public class ActionDto implements Serializable {
    private Long applicationId;
    private Long userId;
    private Long beId;
    private String actionTypeCode;
    private List<ParamDto> paramDtos;
}

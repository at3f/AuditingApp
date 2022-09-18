package com.fawry.auditing_v1.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@Data
public class ActionDto {
    private Long id;
    private String description_en;
    private String description_ar;
    private Date time;

    private Long applicationId;
    private Long userId;
    private Long beId;
    private String actionTypeCode;
    private List<ParamDto> paramDtos;
}

package com.fawry.auditing_v1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParamDto {
    private Long id;
    private String identifier;
    private String value;
    private String name;
}

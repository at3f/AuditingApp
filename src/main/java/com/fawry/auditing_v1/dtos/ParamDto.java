package com.fawry.auditing_v1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParamDto implements Serializable {
    private String identifier;
    private String value;
    private String name;
}

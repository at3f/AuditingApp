package com.fawry.auditing_v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "param_type")
public class ParamType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @JsonIgnore
    private String name_en;
    @JsonIgnore
    private String name_ar;
    @JsonIgnore
    @OneToMany(mappedBy = "paramType")
    private List<Param> params;
}

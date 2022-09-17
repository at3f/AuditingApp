package com.fawry.auditing_v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@Data
@Entity
public class Param {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identifier;
    private String value;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "action_id")
    private Action action;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "param_type_id")
    private ParamType paramType;
}

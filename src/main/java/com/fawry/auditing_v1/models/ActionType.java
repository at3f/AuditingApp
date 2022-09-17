package com.fawry.auditing_v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@Data
@Entity
@Table(name = "action_type")
public class ActionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @JsonIgnore
    private String name_en;
    @JsonIgnore
    private String name_ar;
    @JsonIgnore
    private String message_template_en;
    @JsonIgnore
    private String message_template_ar;
    @JsonIgnore
    @OneToMany(mappedBy = "actionType")
    private List<Action> actions;
}

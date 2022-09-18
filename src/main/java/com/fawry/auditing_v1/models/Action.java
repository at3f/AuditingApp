package com.fawry.auditing_v1.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString
@NoArgsConstructor
@Data
@Entity
@Table(name = "action")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description_en;
    private String description_ar;
    @JsonIgnore
    private Date time;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "be_id")
    private Be be;
    @ManyToOne
    @JoinColumn(name = "action_type_id")
    private ActionType actionType;
    @JsonIgnore
    @OneToMany(mappedBy = "action")
    private List<Param> params;
}

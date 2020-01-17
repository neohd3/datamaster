package com.datamaster.survey.dao.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "survey")
@Data
public class Survey extends AbstractEntity {

    @NotNull
    private String name;

    @NotNull
    private LocalDateTime start;

    @NotNull
    @Column(name = "`end`")
    private LocalDateTime end;

    @NotNull
    private Boolean active;

    @OrderBy(value = "order")
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questions;
}
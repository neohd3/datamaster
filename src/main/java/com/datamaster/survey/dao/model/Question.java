package com.datamaster.survey.dao.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "question")
@Data
public class Question extends AbstractEntity {

    @NotNull
    private String text;

    @NotNull
    @Column(name = "`order`")
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "survey")
    private Survey survey;
}
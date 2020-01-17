package com.datamaster.survey.web.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class AbstractDTO implements Serializable {

    Long id;
    LocalDateTime created;
    LocalDateTime updated;
}
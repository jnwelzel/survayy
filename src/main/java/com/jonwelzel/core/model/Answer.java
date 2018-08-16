package com.jonwelzel.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode
public abstract class Answer {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private Long employeeId;

    @Getter
    @Setter
    private LocalDateTime submittedAt;

    public Answer(Long id, String email, Long employeeId, LocalDateTime submittedAt) {
        this.id = id;
        this.email = email;
        this.employeeId = employeeId;
        this.submittedAt = submittedAt;
    }
}

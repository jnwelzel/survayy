package com.jonwelzel.core.pojo;

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
    private long id;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private long employeeId;

    @Getter
    @Setter
    private LocalDateTime submittedAt;

    public Answer(long id, String email, long employeeId, LocalDateTime submittedAt) {
        this.id = id;
        this.email = email;
        this.employeeId = employeeId;
        this.submittedAt = submittedAt;
    }
}

package com.jonwelzel.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class SingleSelectAnswer extends Answer {

    @Getter
    @Setter
    private SingleSelectQuestion question;

    @Getter
    @Setter
    private String text;

    public SingleSelectAnswer(long id, String email, long employeeId, LocalDateTime submittedAt,
                              SingleSelectQuestion question, String text) {
        super(id, email, employeeId, submittedAt);
        this.question = question;
        this.text = text;
    }
}

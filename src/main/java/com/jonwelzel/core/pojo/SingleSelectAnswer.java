package com.jonwelzel.core.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString(exclude = "question", callSuper = true)
@EqualsAndHashCode
public class SingleSelectAnswer extends Answer {

    @Getter
    @Setter
    private SingleSelectQuestion question;

    @Getter
    @Setter
    private String text;

    public SingleSelectAnswer(Long id, String email, Long employeeId, LocalDateTime submittedAt,
                              SingleSelectQuestion question, String text) {
        super(id, email, employeeId, submittedAt);
        this.question = question;
        this.text = text;
    }
}

package com.jonwelzel.core.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString(exclude = "question", callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RatingAnswer extends Answer {

    @Getter
    @Setter
    private RatingQuestion question;

    @Getter
    @Setter
    private int value;

    public RatingAnswer(long id, String email, long employeeId, LocalDateTime submittedAt, RatingQuestion question, int value) {
        super(id, email, employeeId, submittedAt);
        this.question = question;
        this.value = value;
    }
}

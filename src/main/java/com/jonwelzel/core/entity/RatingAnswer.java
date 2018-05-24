package com.jonwelzel.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *
 * @author jwelzel
 */
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

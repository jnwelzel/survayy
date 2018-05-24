package com.jonwelzel.core.entity;

import java.time.LocalDateTime;

/**
 *
 * @author jwelzel
 */
public class RatingAnswer extends Answer {
    private RatingQuestion question;
    private int value;

    public RatingAnswer(long id, String email, long employeeId, LocalDateTime submittedAt, RatingQuestion question, int value) {
        super(id, email, employeeId, submittedAt);
        this.question = question;
        this.value = value;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(RatingQuestion question) {
        this.question = question;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
}

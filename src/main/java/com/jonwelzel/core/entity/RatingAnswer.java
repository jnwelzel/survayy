package com.jonwelzel.core.entity;

import java.util.Calendar;

/**
 *
 * @author jwelzel
 */
public class RatingAnswer extends Answer {
    private Question question;
    private int value;

    public RatingAnswer(long id, String email, long employeeId, Calendar submittedAt, int value) {
        super(id, email, employeeId, submittedAt);
        this.value = value;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
}

package com.jonwelzel.core.entity;

import java.util.Calendar;

public class SingleSelectAnswer extends Answer {
    private Question question;
    private String text;

    public SingleSelectAnswer(long id, String email, long employeeId, Calendar submittedAt) {
        super(id, email, employeeId, submittedAt);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

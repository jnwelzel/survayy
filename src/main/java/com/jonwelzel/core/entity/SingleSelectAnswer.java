package com.jonwelzel.core.entity;

import java.time.LocalDateTime;

public class SingleSelectAnswer extends Answer {
    private SingleSelectQuestion question;
    private String text;

    public SingleSelectAnswer(long id, String email, long employeeId, LocalDateTime submittedAt,
                              SingleSelectQuestion question, String text) {
        super(id, email, employeeId, submittedAt);
        this.question = question;
        this.text = text;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(SingleSelectQuestion question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

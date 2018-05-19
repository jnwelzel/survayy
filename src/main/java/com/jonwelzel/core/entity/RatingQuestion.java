package com.jonwelzel.core.entity;

import java.util.List;

public class RatingQuestion extends Question {

    private Question question;
    private List<RatingAnswer> answers;

    public RatingQuestion(String theme, String text, Question question, List<RatingAnswer> answers) {
        super(theme, text);
        this.question = question;
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<RatingAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<RatingAnswer> answers) {
        this.answers = answers;
    }
}

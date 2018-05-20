package com.jonwelzel.core.entity;

import java.util.List;

public class RatingQuestion extends Question {

    private List<RatingAnswer> answers;

    public RatingQuestion(long id, String theme, String text, List<RatingAnswer> answers) {
        super(id, theme, text);
        this.answers = answers;
    }

    public List<RatingAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<RatingAnswer> answers) {
        this.answers = answers;
    }
}

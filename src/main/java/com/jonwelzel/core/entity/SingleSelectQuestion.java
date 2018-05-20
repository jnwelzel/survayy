package com.jonwelzel.core.entity;

import java.util.List;

public class SingleSelectQuestion extends Question {

    private List<SingleSelectAnswer> answers;

    public SingleSelectQuestion(long id, String theme, String text, List<SingleSelectAnswer> answers) {
        super(id, theme, text);
        this.answers = answers;
    }

    public List<SingleSelectAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SingleSelectAnswer> answers) {
        this.answers = answers;
    }
}

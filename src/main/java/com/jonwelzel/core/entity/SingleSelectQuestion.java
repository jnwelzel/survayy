package com.jonwelzel.core.entity;

import java.util.List;

public class SingleSelectQuestion extends Question {

    private Question question;
    private List<SingleSelectAnswer> answers;

    public SingleSelectQuestion(String theme, String text, Question question, List<SingleSelectAnswer> answers) {
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

    public List<SingleSelectAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SingleSelectAnswer> answers) {
        this.answers = answers;
    }
}

package com.jonwelzel.core.entity;

public class RatingQuestionAverage {
    private RatingQuestion question;
    private double average;

    public RatingQuestionAverage(RatingQuestion question, double average) {
        this.question = question;
        this.average = average;
    }

    public RatingQuestion getQuestion() {
        return question;
    }

    public void setQuestion(RatingQuestion question) {
        this.question = question;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}

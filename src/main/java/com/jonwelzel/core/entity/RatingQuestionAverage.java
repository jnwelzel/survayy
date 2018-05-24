package com.jonwelzel.core.entity;

import lombok.Getter;
import lombok.Setter;

public class RatingQuestionAverage {

    @Getter
    @Setter
    private RatingQuestion question;

    @Getter
    @Setter
    private double average;

    public RatingQuestionAverage(RatingQuestion question, double average) {
        this.question = question;
        this.average = average;
    }
}

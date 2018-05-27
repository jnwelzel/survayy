package com.jonwelzel.core.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
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

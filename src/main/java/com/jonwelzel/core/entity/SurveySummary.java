package com.jonwelzel.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SurveySummary {

    @Getter
    @Setter
    private double participationPercentage;

    @Getter
    @Setter
    private int totalParticipantCount;

    @Getter
    @Setter
    private List<RatingQuestionAverage> ratingQuestionsAverage;

    public SurveySummary(double participationPercentage, int totalParticipantCount,
                         List<RatingQuestionAverage> ratingQuestionsAverage) {
        this.participationPercentage = participationPercentage;
        this.totalParticipantCount = totalParticipantCount;
        this.ratingQuestionsAverage = ratingQuestionsAverage;
    }
}

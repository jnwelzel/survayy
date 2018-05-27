package com.jonwelzel.core.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@EqualsAndHashCode
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

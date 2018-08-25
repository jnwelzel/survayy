package com.jonwelzel.core.model;

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
    private Double participationPercentage;

    /**
     * People whose response actually have a submission date
     */
    @Getter
    @Setter
    private Integer totalParticipantCount;

    @Getter
    @Setter
    private List<RatingQuestionAverage> ratingQuestionsAverage;

    public SurveySummary(Double participationPercentage, Integer totalParticipantCount,
                         List<RatingQuestionAverage> ratingQuestionsAverage) {
        this.participationPercentage = participationPercentage;
        this.totalParticipantCount = totalParticipantCount;
        this.ratingQuestionsAverage = ratingQuestionsAverage;
    }
}

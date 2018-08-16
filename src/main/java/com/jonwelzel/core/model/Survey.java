package com.jonwelzel.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@EqualsAndHashCode
public class Survey {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private List<RatingQuestion> ratingQuestions;

    @Getter
    @Setter
    private List<SingleSelectQuestion> singleSelectQuestions;

    /**
     * Counts the people who took part in the survey, whether they submitted a survey response or not
     */
    @Getter
    @Setter
    private Integer totalParticipantCount;

    /**
     * Counts only the people who actually submitted a survey response
     */
    @Getter
    @Setter
    private Integer totalResponseCount;

    public Survey(Long id, List<RatingQuestion> ratingQuestions, List<SingleSelectQuestion> singleSelectQuestions,
                  Integer totalParticipantCount, Integer totalResponseCount) {
        this.id = id;
        this.ratingQuestions = ratingQuestions;
        this.singleSelectQuestions = singleSelectQuestions;
        this.totalParticipantCount = totalParticipantCount;
        this.totalResponseCount = totalResponseCount;
    }
}

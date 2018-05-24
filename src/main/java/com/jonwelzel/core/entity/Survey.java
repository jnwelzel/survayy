package com.jonwelzel.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author jwelzel
 */
public class Survey {
    @Getter
    @Setter
    private long id;

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
    private int totalParticipantCount;

    /**
     * Counts only the people who actually submitted a survey response
     */
    @Getter
    @Setter
    private int totalResponseCount;

    public Survey(long id, List<RatingQuestion> ratingQuestions, List<SingleSelectQuestion> singleSelectQuestions,
                  int totalParticipantCount, int totalResponseCount) {
        this.id = id;
        this.ratingQuestions = ratingQuestions;
        this.singleSelectQuestions = singleSelectQuestions;
        this.totalParticipantCount = totalParticipantCount;
        this.totalResponseCount = totalResponseCount;
    }
}

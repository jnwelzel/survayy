package com.jonwelzel.core.entity;

import java.util.List;

/**
 *
 * @author jwelzel
 */
public class Survey {
    private long id;
    private List<RatingQuestion> ratingQuestions;
    private List<SingleSelectQuestion> singleSelectQuestions;

    /**
     * Counts the people who took part in the survey, whether they submitted a survey response or not
     */
    private int totalParticipantCount;

    /**
     * Counts only the people who actually submitted a survey response
     */
    private int totalResponseCount;

    public Survey(long id, List<RatingQuestion> ratingQuestions, List<SingleSelectQuestion> singleSelectQuestions,
                  int totalParticipantCount, int totalResponseCount) {
        this.id = id;
        this.ratingQuestions = ratingQuestions;
        this.singleSelectQuestions = singleSelectQuestions;
        this.totalParticipantCount = totalParticipantCount;
        this.totalResponseCount = totalResponseCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<RatingQuestion> getRatingQuestions() {
        return ratingQuestions;
    }

    public void setRatingQuestions(List<RatingQuestion> ratingQuestions) {
        this.ratingQuestions = ratingQuestions;
    }

    public List<SingleSelectQuestion> getSingleSelectQuestions() {
        return singleSelectQuestions;
    }

    public void setSingleSelectQuestions(List<SingleSelectQuestion> singleSelectQuestions) {
        this.singleSelectQuestions = singleSelectQuestions;
    }

    public int getTotalParticipantCount() {
        return totalParticipantCount;
    }

    public void setTotalParticipantCount(int totalParticipantCount) {
        this.totalParticipantCount = totalParticipantCount;
    }

    public int getTotalResponseCount() {
        return totalResponseCount;
    }

    public void setTotalResponseCount(int totalResponseCount) {
        this.totalResponseCount = totalResponseCount;
    }
}

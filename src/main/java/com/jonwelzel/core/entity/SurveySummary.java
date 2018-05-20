package com.jonwelzel.core.entity;

import java.util.List;

public class SurveySummary {
    private double participationPercentage;
    private int totalParticipantCount;
    private List<RatingQuestionAverage> ratingQuestionsAverage;

    public SurveySummary(double participationPercentage, int totalParticipantCount,
                         List<RatingQuestionAverage> ratingQuestionsAverage) {
        this.participationPercentage = participationPercentage;
        this.totalParticipantCount = totalParticipantCount;
        this.ratingQuestionsAverage = ratingQuestionsAverage;
    }

    public double getParticipationPercentage() {
        return participationPercentage;
    }

    public void setParticipationPercentage(double participationPercentage) {
        this.participationPercentage = participationPercentage;
    }

    public int getTotalParticipantCount() {
        return totalParticipantCount;
    }

    public void setTotalParticipantCount(int totalParticipantCount) {
        this.totalParticipantCount = totalParticipantCount;
    }

    public List<RatingQuestionAverage> getRatingQuestionsAverage() {
        return ratingQuestionsAverage;
    }

    public void setRatingQuestionsAverage(List<RatingQuestionAverage> ratingQuestionsAverage) {
        this.ratingQuestionsAverage = ratingQuestionsAverage;
    }
}

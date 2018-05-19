package com.jonwelzel.core.entity;

import java.util.List;

/**
 *
 * @author jwelzel
 */
public class Survey {
    private long id;
    private List<Question> questions;

    /**
     * Counts the people who took part in the survey, whether they submitted a survey response or not
     */
    private int totalParticipantCount;

    /**
     * Counts only the people who actually submitted a survey response
     */
    private int totalResponseCount;

    public Survey(long id, List<Question> questions, int totalParticipantCount, int totalResponseCount) {
        this.id = id;
        this.questions = questions;
        this.totalParticipantCount = totalParticipantCount;
        this.totalResponseCount = totalResponseCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
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

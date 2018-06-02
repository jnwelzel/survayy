package com.jonwelzel.core.entity;

import com.jonwelzel.core.pojo.Survey;

public final class SurveyEntity {
    private SurveyEntity() {}

    public static Double getParticipationPercentage(Survey survey) {
        if (survey == null || survey.getTotalParticipantCount() == 0 || survey.getTotalResponseCount() == 0) {
            return 0D;
        }

        double totalResponseCount = survey.getTotalResponseCount();
        double totalParticipantCount = survey.getTotalParticipantCount();
        return (totalResponseCount / totalParticipantCount) * 100;
    }
}

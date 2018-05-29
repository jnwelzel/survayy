package com.jonwelzel.core.entity;

import com.jonwelzel.core.pojo.Survey;

public class SurveyEntity {
    public static double getParticipationPercentage(Survey survey) {
        if (survey == null || survey.getTotalParticipantCount() == 0 || survey.getTotalResponseCount() == 0) {
            return 0d;
        }

        double totalResponseCount = survey.getTotalResponseCount();
        double totalParticipantCount = survey.getTotalParticipantCount();
        return (totalResponseCount / totalParticipantCount) * 100;
    }
}

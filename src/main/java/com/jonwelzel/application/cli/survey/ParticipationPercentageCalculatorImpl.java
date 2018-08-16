package com.jonwelzel.application.cli.survey;

import com.jonwelzel.core.model.Survey;
import com.jonwelzel.core.survey.ParticipationPercentageCalculator;

public class ParticipationPercentageCalculatorImpl implements ParticipationPercentageCalculator {
    @Override
    public Double calculateResult(Survey survey) {
        if (survey == null || survey.getTotalParticipantCount() == 0 || survey.getTotalResponseCount() == 0) {
            return 0D;
        }

        double totalResponseCount = survey.getTotalResponseCount();
        double totalParticipantCount = survey.getTotalParticipantCount();
        return (totalResponseCount / totalParticipantCount) * 100;
    }
}

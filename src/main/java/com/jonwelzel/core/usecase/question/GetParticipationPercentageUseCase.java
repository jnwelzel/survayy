package com.jonwelzel.core.usecase.question;

import com.jonwelzel.core.entity.Survey;
import com.jonwelzel.core.gateway.SurveyGateway;

/**
 *
 * @author jwelzel
 */
public class GetParticipationPercentageUseCase {

    private SurveyGateway surveyGateway;

    public GetParticipationPercentageUseCase(SurveyGateway surveyGateway) {
        this.surveyGateway = surveyGateway;
    }

    public double execute(long surveyId) {
        Survey survey = this.surveyGateway.findById(surveyId);

        if (survey == null || survey.getTotalParticipantCount() == 0 || survey.getTotalResponseCount() == 0) {
            return 0d;
        }

        double totalResponseCount = survey.getTotalResponseCount();
        double totalParticipantCount = survey.getTotalParticipantCount();
        return (totalResponseCount / totalParticipantCount) * 100;
    }
}

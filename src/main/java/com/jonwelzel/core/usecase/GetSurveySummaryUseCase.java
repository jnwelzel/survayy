package com.jonwelzel.core.usecase;

import com.jonwelzel.core.entity.SurveySummary;
import com.jonwelzel.core.gateway.SurveyGateway;

public class GetSurveySummaryUseCase {
    private SurveyGateway surveyGateway;

    public GetSurveySummaryUseCase(SurveyGateway surveyGateway) {
        this.surveyGateway = surveyGateway;
    }

    public SurveySummary execute(long surveyId) {
        return null;
    }
}

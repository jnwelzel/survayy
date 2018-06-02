package com.jonwelzel.core.usecase;

import com.jonwelzel.core.gateway.survey.SurveyDataParseException;
import com.jonwelzel.core.gateway.survey.SurveyGateway;
import com.jonwelzel.core.pojo.Survey;
import com.jonwelzel.core.pojo.SurveySummary;
import com.jonwelzel.core.presenter.SurveySummaryPresenter;

import static com.jonwelzel.core.entity.SurveyEntity.getParticipationPercentage;

import static com.jonwelzel.core.entity.RatingQuestionAverageEntity.getRatingQuestionsAverage;

public class GetSurveySummaryUseCase {
    private SurveyGateway surveyGateway;
    private SurveySummaryPresenter surveySummaryPresenter;

    public GetSurveySummaryUseCase(SurveyGateway surveyGateway, SurveySummaryPresenter surveySummaryPresenter) {
        this.surveyGateway = surveyGateway;
        this.surveySummaryPresenter = surveySummaryPresenter;
    }

    public void execute(Object rawData) {
        try {
            Survey survey = this.surveyGateway.getSurveyFromRawData(rawData);
            this.surveySummaryPresenter.presentSuccess(new SurveySummary(getParticipationPercentage(survey),
                    survey.getTotalResponseCount(), getRatingQuestionsAverage(survey.getRatingQuestions())));
        } catch (SurveyDataParseException surveyDataParseError) {
            this.surveySummaryPresenter.presentError(surveyDataParseError.getMessage());
        }

    }
}

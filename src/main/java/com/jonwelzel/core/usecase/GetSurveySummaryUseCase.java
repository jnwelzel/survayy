package com.jonwelzel.core.usecase;

import com.jonwelzel.core.gateway.survey.SurveyDataParseError;
import com.jonwelzel.core.gateway.survey.SurveyGateway;
import com.jonwelzel.core.pojo.Survey;
import com.jonwelzel.core.pojo.SurveySummary;
import com.jonwelzel.core.presenter.SurveySummaryPresenter;

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
                    survey.getTotalParticipantCount(), getRatingQuestionsAverage(survey.getRatingQuestions())));
        } catch (SurveyDataParseError surveyDataParseError) {
            this.surveySummaryPresenter.presentError(surveyDataParseError.getMessage());
        }

    }

    private double getParticipationPercentage(Survey survey) {
        if (survey == null || survey.getTotalParticipantCount() == 0 || survey.getTotalResponseCount() == 0) {
            return 0d;
        }

        double totalResponseCount = survey.getTotalResponseCount();
        double totalParticipantCount = survey.getTotalParticipantCount();
        return (totalResponseCount / totalParticipantCount) * 100;
    }
}

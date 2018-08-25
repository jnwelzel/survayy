package com.jonwelzel.core.usecase;

import com.jonwelzel.core.gateway.survey.SurveyDataParseException;
import com.jonwelzel.core.gateway.survey.SurveyGateway;
import com.jonwelzel.core.model.Survey;
import com.jonwelzel.core.model.SurveySummary;
import com.jonwelzel.core.presenter.SurveySummaryPresenter;
import com.jonwelzel.core.survey.ParticipationPercentageCalculator;
import com.jonwelzel.core.survey.RatingQuestionsAverageCalculator;

//import static com.jonwelzel.core.entity.SurveyEntity.getParticipationPercentage;
//import static com.jonwelzel.core.entity.RatingQuestionAverageEntity.calculateResult;

public class SurveySummariser {
    private SurveyGateway surveyGateway;
    private SurveySummaryPresenter surveySummaryPresenter;
    private ParticipationPercentageCalculator participationPercentageCalculator;
    private RatingQuestionsAverageCalculator ratingQuestionsAverageCalculator;

    public SurveySummariser(SurveyGateway surveyGateway, SurveySummaryPresenter surveySummaryPresenter,
                            ParticipationPercentageCalculator participationPercentageCalculator,
                            RatingQuestionsAverageCalculator ratingQuestionsAverageCalculator) {
        this.surveyGateway = surveyGateway;
        this.surveySummaryPresenter = surveySummaryPresenter;
        this.participationPercentageCalculator = participationPercentageCalculator;
        this.ratingQuestionsAverageCalculator = ratingQuestionsAverageCalculator;
    }

    public void execute(Object rawData) {
        try {
            Survey survey = this.surveyGateway.getSurveyFromRawData(rawData);
            this.surveySummaryPresenter.presentSuccess(new SurveySummary(
                    this.participationPercentageCalculator.calculateResult(survey),
                    survey.getTotalResponseCount(),
                    this.ratingQuestionsAverageCalculator.calculateResult(survey.getRatingQuestions())));
        } catch (SurveyDataParseException surveyDataParseError) {
            this.surveySummaryPresenter.presentError(surveyDataParseError.getMessage());
        }

    }
}

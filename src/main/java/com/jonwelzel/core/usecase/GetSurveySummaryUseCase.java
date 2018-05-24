package com.jonwelzel.core.usecase;

import com.jonwelzel.core.entity.*;
import com.jonwelzel.core.gateway.SurveyGateway;
import com.jonwelzel.core.utils.Math;

import java.util.ArrayList;
import java.util.List;

public class GetSurveySummaryUseCase {
    private SurveyGateway surveyGateway;

    public GetSurveySummaryUseCase(SurveyGateway surveyGateway) {
        this.surveyGateway = surveyGateway;
    }

    public SurveySummary execute(long surveyId) {
        final Survey survey = this.surveyGateway.findById(surveyId);

        return new SurveySummary(getParticipationPercentage(survey),
                survey.getTotalParticipantCount(), getRatingQuestionsAverage(survey.getRatingQuestions()));
    }

    private double getParticipationPercentage(Survey survey) {
        if (survey == null || survey.getTotalParticipantCount() == 0 || survey.getTotalResponseCount() == 0) {
            return 0d;
        }

        double totalResponseCount = survey.getTotalResponseCount();
        double totalParticipantCount = survey.getTotalParticipantCount();
        return (totalResponseCount / totalParticipantCount) * 100;
    }

    private List<RatingQuestionAverage> getRatingQuestionsAverage(List<RatingQuestion> questions) {
        List<RatingQuestionAverage> ratingQuestionsAverage = new ArrayList<>();
        questions.forEach(question -> ratingQuestionsAverage.add(new RatingQuestionAverage(question,
                getSingleRatingQuestionAverage(question.getAnswers()))));

        return ratingQuestionsAverage;
    }

    private double getSingleRatingQuestionAverage(List<RatingAnswer> answers) {
        if (answers.isEmpty()) {
            return 0d;
        }

        int scoreSum = 0;
        scoreSum = answers.stream().map(RatingAnswer::getValue).reduce(scoreSum, Integer::sum);
        double score = scoreSum;

        return Math.round(score / answers.size(), 2);
    }
}

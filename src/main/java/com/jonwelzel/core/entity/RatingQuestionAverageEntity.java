package com.jonwelzel.core.entity;

import com.jonwelzel.core.pojo.RatingAnswer;
import com.jonwelzel.core.pojo.RatingQuestion;
import com.jonwelzel.core.pojo.RatingQuestionAverage;
import com.jonwelzel.core.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class RatingQuestionAverageEntity {
    public static List<RatingQuestionAverage> getRatingQuestionsAverage(List<RatingQuestion> questions) {
        if (questions == null || questions.isEmpty()) {
            return new ArrayList<>();
        }

        List<RatingQuestionAverage> ratingQuestionsAverage = new ArrayList<>();
        questions.forEach(question -> ratingQuestionsAverage.add(new RatingQuestionAverage(question,
                getSingleRatingQuestionAverage(question.getAnswers()))));

        return ratingQuestionsAverage;
    }

    private static double getSingleRatingQuestionAverage(List<RatingAnswer> answers) {
        if (answers.isEmpty()) {
            return 0d;
        }

        Integer scoreSum = 0;
        Integer validAnswersCount = answers.stream().filter(answer -> answer.getSubmittedAt() != null).toArray().length;
        for (RatingAnswer answer : answers) {
            if (answer.getValue() != null && answer.getSubmittedAt() != null) {
                scoreSum += answer.getValue();
            }
        }
        double score = scoreSum;

        return MathUtils.round(score / validAnswersCount, 2);
    }
}

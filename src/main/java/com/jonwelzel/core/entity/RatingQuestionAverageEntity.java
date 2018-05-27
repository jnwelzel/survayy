package com.jonwelzel.core.entity;

import com.jonwelzel.core.pojo.RatingAnswer;
import com.jonwelzel.core.pojo.RatingQuestion;
import com.jonwelzel.core.pojo.RatingQuestionAverage;
import com.jonwelzel.core.utils.Math;

import java.util.ArrayList;
import java.util.List;

public class RatingQuestionAverageEntity {
    public static List<RatingQuestionAverage> getRatingQuestionsAverage(List<RatingQuestion> questions) {
        List<RatingQuestionAverage> ratingQuestionsAverage = new ArrayList<>();
        questions.forEach(question -> ratingQuestionsAverage.add(new RatingQuestionAverage(question,
                getSingleRatingQuestionAverage(question.getAnswers()))));

        return ratingQuestionsAverage;
    }

    private static double getSingleRatingQuestionAverage(List<RatingAnswer> answers) {
        if (answers.isEmpty()) {
            return 0d;
        }

        int scoreSum = 0;
        scoreSum = answers.stream().map(RatingAnswer::getValue).reduce(scoreSum, Integer::sum);
        double score = scoreSum;

        return Math.round(score / answers.size(), 2);
    }
}

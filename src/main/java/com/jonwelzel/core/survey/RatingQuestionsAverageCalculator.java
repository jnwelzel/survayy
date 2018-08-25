package com.jonwelzel.core.survey;

import com.jonwelzel.core.model.RatingQuestion;
import com.jonwelzel.core.model.RatingQuestionAverage;

import java.util.List;

public interface RatingQuestionsAverageCalculator {
    List<RatingQuestionAverage> calculateResult(List<RatingQuestion> questions);
}

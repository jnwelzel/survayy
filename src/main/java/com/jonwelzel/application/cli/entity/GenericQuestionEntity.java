package com.jonwelzel.application.cli.entity;

import com.jonwelzel.application.cli.exception.InvalidParametersException;
import com.jonwelzel.application.cli.pojo.GenericQuestion;
import com.jonwelzel.application.cli.pojo.QuestionHeaderPositions;
import com.jonwelzel.application.cli.pojo.QuestionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GenericQuestionEntity {
    private GenericQuestionEntity() {}

    public static List<GenericQuestion> extractGenericQuestions(List<String[]> surveyQuestions,
                                                                List<String[]> surveyResponses,
                                                                QuestionHeaderPositions headerPositions) throws InvalidParametersException {
        List<GenericQuestion> genericQuestions = new ArrayList<>();

        for (int i = 1; i < surveyQuestions.size(); i++) {
            List<String> currentQuestion = Arrays.asList(surveyQuestions.get(i));
            String theme = currentQuestion.get(headerPositions.getTheme());
            String text = currentQuestion.get(headerPositions.getText());
            QuestionType questionType = QuestionType.byString(currentQuestion.get(headerPositions.getType()));

            genericQuestions.add(new GenericQuestion(theme, text, questionType,
                    GenericAnswerEntity.extractGenericAnswers(i, surveyResponses, questionType)));
        }

        return genericQuestions;
    }
}

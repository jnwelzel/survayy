package com.jonwelzel.application.cli.entity;

import com.jonwelzel.application.cli.exception.InvalidParametersException;
import com.jonwelzel.application.cli.model.QuestionType;
import com.jonwelzel.application.cli.model.GenericAnswer;

import java.util.ArrayList;
import java.util.List;

public final class GenericAnswerEntity {
    private GenericAnswerEntity() {}

    public static List<GenericAnswer> extractGenericAnswers(Integer currentQuestionIndex,
                                                            List<String[]> surveyResponses, QuestionType questionType)
            throws InvalidParametersException {
        failIfInvalidArguments(currentQuestionIndex, surveyResponses, questionType);

        List<GenericAnswer> answers = new ArrayList<>();
        final int answersStartPosition = 2;
        final int emailPosition = 0;
        final int employeeIdPosition = 1;
        final int submittedAtPostion = 2;

        for (int j = 0; j < surveyResponses.size(); j++) {
            int currentAnswerIndex = currentQuestionIndex + answersStartPosition;
            answers.add(new GenericAnswer(surveyResponses.get(j)[emailPosition],
                    surveyResponses.get(j)[employeeIdPosition], surveyResponses.get(j)[submittedAtPostion],
                    surveyResponses.get(j)[currentAnswerIndex], questionType));
        }

        return answers;
    }

    private static void failIfInvalidArguments(Integer currentQuestionIndex, List<String[]> surveyResponses,
                                               QuestionType questionType) throws InvalidParametersException {
        if (currentQuestionIndex == null || surveyResponses == null || questionType == null) {
            throw new InvalidParametersException("To extract the answers, none of the parameters should be null or missing");
        }
    }
}

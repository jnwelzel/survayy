package com.jonwelzel.application.cli.entity;

import com.jonwelzel.application.cli.pojo.QuestionType;
import com.jonwelzel.application.cli.pojo.GenericAnswer;

import java.util.ArrayList;
import java.util.List;

public class GenericAnswerEntity {
    public static List<GenericAnswer> extractGenericAnswers(Integer currentQuestionIndex,
                                                            List<String[]> surveyResponses, QuestionType questionType) {
        failIfInvalidArguments(currentQuestionIndex, surveyResponses);

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

    private static void failIfInvalidArguments(Integer currentQuestionIndex, List<String[]> surveyResponses) {
        if (currentQuestionIndex == null || surveyResponses == null) {
            throw new RuntimeException("To extract the answers, none of the parameters should be null.");
        }
    }
}

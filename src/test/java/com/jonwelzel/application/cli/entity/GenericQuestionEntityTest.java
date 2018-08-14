package com.jonwelzel.application.cli.entity;

import com.jonwelzel.application.cli.exception.InvalidParametersException;
import com.jonwelzel.application.cli.model.GenericQuestion;
import com.jonwelzel.application.cli.model.QuestionHeaderPositions;
import com.jonwelzel.application.cli.model.QuestionType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GenericQuestionEntityTest {
    @Test
    public void should_extract_questions_from_the_raw_data() throws InvalidParametersException {
        // Survey questions
        List<String[]> surveyQuestions = new ArrayList<>();
        String[] surveyQuestionHeadersPositions = new String[]{"theme", "text", "type"};
        QuestionHeaderPositions questionHeaderPositions = new QuestionHeaderPositions(2, 0, 1);
        String[] surveyQuestion1 = new String[]{"Work", "I like my job.", "ratingquestion"};
        surveyQuestions.add(surveyQuestionHeadersPositions);
        surveyQuestions.add(surveyQuestion1);

        // Survey responses
        String[] surveyResponseRawData1 = new String[]{"email1@email.com", "100", "2014-07-28T20:35:41+00:00", "5"};
        String[] surveyResponseRawData2 = new String[]{"email2@email.com", "101", "2014-07-29T16:14:43+00:00", "4"};
        List<String[]> surveyResponses = new ArrayList<>();
        surveyResponses.add(surveyResponseRawData1);
        surveyResponses.add(surveyResponseRawData2);

        List<GenericQuestion> result = GenericQuestionEntity.extractGenericQuestions(surveyQuestions, surveyResponses,
                questionHeaderPositions);

        assertThat(result.get(0).getQuestionType()).isEqualTo(QuestionType.RATING);
        assertThat(result.get(0).getAnswers().size()).isEqualTo(2);
        assertThat(result.get(0).getAnswers().get(0).getAnswerValue()).isEqualTo(surveyResponseRawData1[3]);
    }
}

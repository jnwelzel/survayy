package com.jonwelzel.application.cli.entity;

import com.jonwelzel.application.cli.exception.InvalidParametersException;
import com.jonwelzel.application.cli.model.GenericAnswer;
import com.jonwelzel.application.cli.model.QuestionType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GenericAnswerEntityTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_throw_exception_when_one_or_more_parameters_are_invalid() throws InvalidParametersException {
        expectedException.expect(InvalidParametersException.class);
        expectedException.expectMessage("To extract the answers, none of the parameters should be null or missing");

        GenericAnswerEntity.extractGenericAnswers(null, null, null);
    }

    @Test
    public void should_extract_answers_from_the_data_provided() throws InvalidParametersException {
        Integer currentQuestionIndex = 1;
        String[] surveyResponseRawData1 = new String[]{"email1@email.com", "100", "2014-07-28T20:35:41+00:00", "5"};
        String[] surveyResponseRawData2 = new String[]{"email2@email.com", "101", "2014-07-29T16:14:43+00:00", "4"};
        List<String[]> surveyResponses = new ArrayList<>();
        surveyResponses.add(surveyResponseRawData1);
        surveyResponses.add(surveyResponseRawData2);
        QuestionType questionType = QuestionType.RATING;

        List<GenericAnswer> result = GenericAnswerEntity.extractGenericAnswers(currentQuestionIndex, surveyResponses,
                questionType);

        assertThat(result.get(0).getSubmittedAtValue()).isEqualTo(surveyResponseRawData1[2]);
        assertThat(result.get(1).getSubmittedAtValue()).isEqualTo(surveyResponseRawData2[2]);
    }
}

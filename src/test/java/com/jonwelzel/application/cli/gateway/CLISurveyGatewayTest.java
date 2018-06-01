package com.jonwelzel.application.cli.gateway;

import com.jonwelzel.application.cli.entity.GenericQuestionEntity;
import com.jonwelzel.application.cli.pojo.GenericQuestion;
import com.jonwelzel.application.cli.pojo.QuestionHeaderPositions;
import com.jonwelzel.application.cli.pojo.QuestionType;
import com.jonwelzel.core.gateway.survey.SurveyDataParseError;
import com.jonwelzel.core.pojo.Survey;
import com.opencsv.CSVReader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CLISurveyGatewayTest {
    private String surveyFileFullPath;
    private String responsesFileFullPath;
    private String invalidQuestionHeaderFileFullPath;

    @Before
    public void setUp() {
        String surveyFile = "csv/survey-3.csv";
        this.surveyFileFullPath = new File(getClass().getClassLoader().getResource(surveyFile).getFile()).getPath();
        String responsesFile = "csv/survey-3-resonses.csv";
        this.responsesFileFullPath = new File(getClass().getClassLoader().getResource(responsesFile).getFile()).getPath();
        String surveyInvalidQuestionHeaderFile = "csv/survey-invalid-question-header.csv";
        this.invalidQuestionHeaderFileFullPath = new File(getClass().getClassLoader().
                getResource(surveyInvalidQuestionHeaderFile).getFile()).getPath();
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_read_valid_csv_files_without_any_errors() throws SurveyDataParseError {
        Survey survey = new CLISurveyGateway().getSurveyFromRawData(new String[]{this.surveyFileFullPath,
                this.responsesFileFullPath});

        assertThat(survey).isNotNull();
    }

    @Test
    public void should_fail_when_file_paths_are_invalid() throws SurveyDataParseError {
        expectedException.expect(SurveyDataParseError.class);

        new CLISurveyGateway().getSurveyFromRawData(new String[]{"/foo/bar/survey.csv", "/foo/bar/responses.csv"});
    }

    @Test
    public void should_fail_when_either_file_path_is_missing() throws SurveyDataParseError {
        expectedException.expect(SurveyDataParseError.class);
        expectedException.expectMessage("Both survey and survey response file paths are required.");

        new CLISurveyGateway().getSurveyFromRawData(new String[]{"/foo/bar/responses.csv"});
    }

    @Test
    public void should_fail_when_parameter_is_invalid() throws SurveyDataParseError {
        expectedException.expect(SurveyDataParseError.class);
        expectedException.expectMessage("Parameter must be an instance of 'String[]'");

        new CLISurveyGateway().getSurveyFromRawData(12);
    }

    @Test
    public void should_fail_when_question_header_is_invalid() throws SurveyDataParseError {
        String expectedMessage = "The question headers should all be present and follow the pattern: ['theme', 'type', 'text']";
        expectedException.expect(SurveyDataParseError.class);
        expectedException.expectMessage(expectedMessage);

        new CLISurveyGateway().getSurveyFromRawData(new String[]{this.invalidQuestionHeaderFileFullPath,
                this.responsesFileFullPath});
    }

    @Test
    public void should_correctly_count_the_participants() throws SurveyDataParseError {
        int expectedTotalParticipantCount = 5;

        Survey result = new CLISurveyGateway().getSurveyFromRawData(new String[]{this.surveyFileFullPath,
                this.responsesFileFullPath});

        assertThat(result.getTotalParticipantCount()).isEqualTo(expectedTotalParticipantCount);
    }

    @Test
    public void should_correctly_count_the_valid_responses() throws SurveyDataParseError {
        int expectedTotalValidResponsesCount = 0;

        Survey result = new CLISurveyGateway().getSurveyFromRawData(new String[]{this.surveyFileFullPath,
                this.responsesFileFullPath});

        assertThat(result.getTotalResponseCount()).isEqualTo(expectedTotalValidResponsesCount);
    }

    @Test
    public void should_extract_the_correct_number_of_questions_from_the_data() throws SurveyDataParseError {
        int expectedRatingQuestionsCount = 3;
        int expectedSingleSelectQuestionsCount = 2;

        Survey result = new CLISurveyGateway().getSurveyFromRawData(new String[]{this.surveyFileFullPath,
                this.responsesFileFullPath});

        assertThat(result.getRatingQuestions().size()).isEqualTo(expectedRatingQuestionsCount);
        assertThat(result.getSingleSelectQuestions().size()).isEqualTo(expectedSingleSelectQuestionsCount);
    }

    @Test
    public void should_have_the_same_number_of_answers_for_every_question() throws SurveyDataParseError {
        int expectedAnswersCount = 5;

        Survey result = new CLISurveyGateway().getSurveyFromRawData(new String[]{this.surveyFileFullPath,
                this.responsesFileFullPath});

        assertThat(result.getRatingQuestions().get(0).getAnswers().size()).isEqualTo(expectedAnswersCount);
        assertThat(result.getRatingQuestions().get(1).getAnswers().size()).isEqualTo(expectedAnswersCount);
        assertThat(result.getRatingQuestions().get(2).getAnswers().size()).isEqualTo(expectedAnswersCount);
        assertThat(result.getSingleSelectQuestions().get(0).getAnswers().size()).isEqualTo(expectedAnswersCount);
        assertThat(result.getSingleSelectQuestions().get(1).getAnswers().size()).isEqualTo(expectedAnswersCount);
    }
}

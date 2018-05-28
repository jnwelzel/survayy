package com.jonwelzel.application.cli.gateway;

import com.jonwelzel.core.pojo.RatingQuestion;
import com.jonwelzel.core.pojo.SingleSelectQuestion;
import com.jonwelzel.core.pojo.Survey;
import com.opencsv.CSVReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CLISurveyGatewayTest {
    private CSVReader csvReader;
    private File surveyCsvFile;
    private File surveyResponsesCsvFile;

    @Before
    public void setUp() {
        // Survey file
        String surveyCsvFilePath = "csv/survey-3.csv";
        this.surveyCsvFile = new File(getClass().getClassLoader().getResource(surveyCsvFilePath).getFile());

        // Responses file
        String surveyResponsesCsvFilePath = "csv/survey-3-resonses.csv";
        this.surveyResponsesCsvFile = new File(getClass().getClassLoader().getResource(surveyResponsesCsvFilePath)
                .getFile());
    }

    @Test
    public void should_read_a_valid_csv_file_without_any_errors() throws IOException {
        final int expectedLineCount = 6;

        this.csvReader = new CSVReader(new FileReader(this.surveyCsvFile));
        List<String[]> csvLines = this.csvReader.readAll();

        assertThat(csvLines.size()).isEqualTo(expectedLineCount);
    }

    @Test
    public void should_count_how_many_participants_were_in_the_survey() throws IOException {
        final int expectedTotalParticipantCount = 5;

        this.csvReader = new CSVReader(new FileReader(this.surveyResponsesCsvFile));
        List<String[]> csvLines = this.csvReader.readAll();

        assertThat(csvLines.size()).isEqualTo(expectedTotalParticipantCount);
    }

    @Test
    public void survey_header_should_contain_the_type_the_theme_and_the_text_of_the_questions() throws IOException {
        final List<String> expectedHeaders = new ArrayList<>(Arrays.asList("type", "theme", "text"));
        boolean containsAllExpectedHeaders = true;

        this.csvReader = new CSVReader(new FileReader(this.surveyCsvFile));
        List<String[]> csvLines = this.csvReader.readAll();
        String[] headers = csvLines.get(0);
        for (String header : headers) {
            if (!expectedHeaders.contains(header)) {
                containsAllExpectedHeaders = false;
                break;
            }
        }

        assertThat(containsAllExpectedHeaders).isTrue();
    }

    @Test
    public void should_separate_the_questions_by_their_type() throws IOException {
        final int expectedRatingQuestionCount = 3;
        final int expectedSingleSelectQuestionCount = 2;
        AtomicLong questionIds = new AtomicLong();

        List<RatingQuestion> ratingQuestions = new ArrayList<>();
        List<SingleSelectQuestion> singleSelectQuestions = new ArrayList<>();
        this.csvReader = new CSVReader(new FileReader(this.surveyCsvFile));
        List<String[]> csvLines = this.csvReader.readAll();
        List<String> headers = Arrays.asList(csvLines.get(0));
        int typeHeaderPosition = headers.indexOf("type");
        int themeHeaderPosition = headers.indexOf("theme");
        int textHeaderPosition = headers.indexOf("text");
        for (int i = 1; i < csvLines.size(); i++) {
            List<String> currentQuestion = Arrays.asList(csvLines.get(i));
            long id = questionIds.getAndIncrement();
            final String theme = currentQuestion.get(themeHeaderPosition);
            final String text = currentQuestion.get(textHeaderPosition);

            if (currentQuestion.get(typeHeaderPosition).equals("ratingquestion")) {
                ratingQuestions.add(new RatingQuestion(id, theme, text, new ArrayList<>()));
            } else {
                singleSelectQuestions.add(new SingleSelectQuestion(id, theme, text, new ArrayList<>()));
            }
        }

        assertThat(ratingQuestions.size()).isEqualTo(expectedRatingQuestionCount);
        assertThat(singleSelectQuestions.size()).isEqualTo(expectedSingleSelectQuestionCount);
    }

    @Test
    public void should_read_survey_files_and_return_a_survey_object() {
        final String fakeSurveyFilePath = "/home/jwelzel/Docs/Surveys/survey-1.csv";
        final String fakeSurveyResponseFilePath = "/home/jwelzel/Docs/Surveys/survey-1-responses.csv";
        final CLISurveyGateway surveyGateway = new CLISurveyGateway(fakeSurveyFilePath, fakeSurveyResponseFilePath);

        final Survey result = surveyGateway.getSurveyFromRawData(1);
        final int expectedParticipantCount = 3;

        assertThat(result.getTotalParticipantCount()).isEqualTo(expectedParticipantCount);
    }
}

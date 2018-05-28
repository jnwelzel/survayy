package com.jonwelzel.application.cli.gateway;

import com.jonwelzel.core.pojo.Survey;
import com.opencsv.CSVReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CLISurveyGatewayTest {
    private CSVReader csvReader;
    private File csvFile;
    private final String csvFilePath = "survey-3.csv";

    @Before
    public void setUp() throws Exception {
        this.csvFile = new File(getClass().getClassLoader().getResource(csvFilePath).getFile());
        this.csvReader = new CSVReader(new FileReader(this.csvFile));
    }

    @Test
    public void should_read_a_csv_file_without_any_errors() throws IOException {
        final int expectedLineCount = 6;

        List<String[]> csvLines = this.csvReader.readAll();

        assertThat(csvLines.size()).isEqualTo(expectedLineCount);
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

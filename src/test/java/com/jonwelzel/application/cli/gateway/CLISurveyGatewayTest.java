package com.jonwelzel.application.cli.gateway;

import com.jonwelzel.core.entity.Survey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CLISurveyGatewayTest {
    @Test
    public void readsSurveyFilesAndReturnsASurveyObject() {
        final String fakeSurveyFilePath = "/home/jwelzel/Docs/Surveys/survey-1.csv";
        final String fakeSurveyResponseFilePath = "/home/jwelzel/Docs/Surveys/survey-1-responses.csv";
        final CLISurveyGateway surveyGateway = new CLISurveyGateway(fakeSurveyFilePath, fakeSurveyResponseFilePath);

        final Survey result = surveyGateway.findById(1);
        final int expectedParticipantCount = 3;

        assertThat(result.getTotalParticipantCount()).isEqualTo(expectedParticipantCount);
    }
}

package com.jonwelzel.core.usecase.question;

import com.jonwelzel.core.entity.Survey;
import com.jonwelzel.core.gateway.SurveyGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class GetParticipationPercentageUseCaseTest {

    @Mock
    private SurveyGateway surveyGateway;

    private final long SURVEY_ID = 1l;

    @Test
    public void calculatesThePercentageOfEmployeesThatParticipatedInTheSurvey() {
        final int totalParticipantCount = 10;
        final int totalResponseCount = 5;
        final double expectedParticipationPercentage = 50d;
        final Survey surveyFromGateway = new Survey(SURVEY_ID, null, null, totalParticipantCount, totalResponseCount);
        given(surveyGateway.findById(SURVEY_ID)).willReturn(surveyFromGateway);

        GetParticipationPercentageUseCase useCase = new GetParticipationPercentageUseCase(surveyGateway);
        final double result = useCase.execute(SURVEY_ID);

        assertThat(result).isEqualTo(expectedParticipationPercentage);
    }

    @Test
    public void participationPercentageShouldBeZeroWhenNoResponsesWereSubmitted() {
        final int totalParticipantCount = 10;
        final int totalResponseCount = 0;
        final double expectedParticipationPercentage = 0d;
        final Survey surveyFromGateway = new Survey(SURVEY_ID, null, null, totalParticipantCount, totalResponseCount);
        given(surveyGateway.findById(SURVEY_ID)).willReturn(surveyFromGateway);

        GetParticipationPercentageUseCase useCase = new GetParticipationPercentageUseCase(surveyGateway);
        final double result = useCase.execute(SURVEY_ID);

        assertThat(result).isEqualTo(expectedParticipationPercentage);
    }

}

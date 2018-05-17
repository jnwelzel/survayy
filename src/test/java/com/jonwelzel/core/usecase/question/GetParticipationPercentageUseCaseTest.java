package com.jonwelzel.core.usecase.question;

import com.jonwelzel.core.entity.Answer;
import com.jonwelzel.core.entity.Question;
import com.jonwelzel.core.gateway.RatingQuestionAnswerGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class GetParticipationPercentageUseCaseTest {

    @Mock
    private RatingQuestionAnswerGateway ratingQuestionAnswerGateway;

    @Test
    public void calculatesThePercentageOfEmployeesThatParticipatedInTheSurvey() {
        final List<Question> allSurveyQuestions = new ArrayList<>();
        final double expectedParticipationPercentage = 100d;

        GetParticipationPercentageUseCase useCase = new GetParticipationPercentageUseCase(ratingQuestionAnswerGateway);

        final double result = useCase.execute(allSurveyQuestions);

        assertThat(result).isEqualTo(expectedParticipationPercentage);
    }
}

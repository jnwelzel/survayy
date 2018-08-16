package com.jonwelzel.core.entity;

import com.jonwelzel.core.model.Survey;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SurveyEntityTest {

    @Test
    public void when_survey_is_falsey_should_return_participation_percentage_zero() {
        double expectedParticipationPercentage = 0D;

        Double result = SurveyEntity.getParticipationPercentage(null);

        Assertions.assertThat(result).isEqualTo(expectedParticipationPercentage);
    }

    @Test
    public void when_survey_is_truthy_should_return_the_correct_participation_percentage_value() {
        Double expectedParticipationPercentage = 75D;

        Survey survey = new Survey(1L, null, null, 4, 3);
        Double result = SurveyEntity.getParticipationPercentage(survey);

        Assertions.assertThat(result).isEqualTo(expectedParticipationPercentage);
    }
}

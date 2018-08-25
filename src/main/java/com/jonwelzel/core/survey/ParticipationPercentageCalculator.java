package com.jonwelzel.core.survey;

import com.jonwelzel.core.model.Survey;

public interface ParticipationPercentageCalculator {
    Double calculateResult(Survey survey);
}

package com.jonwelzel.core.gateway;

import com.jonwelzel.core.entity.Survey;

public interface SurveyGateway {
    Survey findById(long surveyId);
}

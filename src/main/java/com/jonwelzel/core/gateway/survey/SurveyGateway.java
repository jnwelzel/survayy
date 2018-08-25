package com.jonwelzel.core.gateway.survey;

import com.jonwelzel.core.model.Survey;

public interface SurveyGateway {
    Survey getSurveyFromRawData(Object rawData) throws SurveyDataParseException;
}

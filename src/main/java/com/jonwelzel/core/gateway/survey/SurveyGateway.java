package com.jonwelzel.core.gateway.survey;

import com.jonwelzel.core.pojo.Survey;

public interface SurveyGateway {
    Survey getSurveyFromRawData(Object rawData) throws SurveyDataParseException;
}

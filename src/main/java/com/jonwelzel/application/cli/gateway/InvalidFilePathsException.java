package com.jonwelzel.application.cli.gateway;

import com.jonwelzel.core.gateway.survey.SurveyDataParseError;

public class InvalidFilePathsException extends SurveyDataParseError {
    public InvalidFilePathsException() {
        super("Both survey and survey response file paths are required.");
    }
}

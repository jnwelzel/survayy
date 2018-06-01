package com.jonwelzel.application.cli.exception;

import com.jonwelzel.core.gateway.survey.SurveyDataParseException;

public class InvalidFilePathsException extends SurveyDataParseException {
    public InvalidFilePathsException() {
        super("Both survey and survey response file paths are required.");
    }
}

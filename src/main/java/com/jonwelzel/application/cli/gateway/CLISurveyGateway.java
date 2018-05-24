package com.jonwelzel.application.cli.gateway;

import com.jonwelzel.core.entity.Survey;
import com.jonwelzel.core.gateway.SurveyGateway;

public class CLISurveyGateway implements SurveyGateway {
    private String surveyFilePath;
    private String surveyResponseFilePath;

    public CLISurveyGateway(String surveyFilePath, String surveyResponseFilePath) {
        this.surveyFilePath = surveyFilePath;
        this.surveyResponseFilePath = surveyResponseFilePath;
    }

    @Override
    public Survey findById(long surveyId) {
        failIfAnyPathsAreMissing();
        return null;
    }

    private void failIfAnyPathsAreMissing() {
        if (this.surveyFilePath == null || this.surveyFilePath.equals("") || this.surveyResponseFilePath == null ||
                this.surveyResponseFilePath.equals("")) {
            throw new InvalidFilePathsException();
        }
    }

    public String getSurveyFilePath() {
        return surveyFilePath;
    }

    public void setSurveyFilePath(String surveyFilePath) {
        this.surveyFilePath = surveyFilePath;
    }

    public String getSurveyResponseFilePath() {
        return surveyResponseFilePath;
    }

    public void setSurveyResponseFilePath(String surveyResponseFilePath) {
        this.surveyResponseFilePath = surveyResponseFilePath;
    }
}

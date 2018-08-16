package com.jonwelzel.application.cli.builder;

import com.jonwelzel.application.cli.gateway.CLISurveyGateway;
import com.jonwelzel.application.cli.presenter.CLISurveySummaryPresenter;
import com.jonwelzel.core.usecase.SurveySummariser;

public class CLIGetSurveySummaryUseCaseBuilder {
    private CLISurveyGateway gateway;
    private CLISurveySummaryPresenter presenter;

    public CLIGetSurveySummaryUseCaseBuilder() {
        this.gateway = new CLISurveyGateway();
        this.presenter = new CLISurveySummaryPresenter();
    }

    public SurveySummariser getUseCase() {
        return new SurveySummariser(this.gateway, this.presenter);
    }
}

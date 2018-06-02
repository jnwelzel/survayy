package com.jonwelzel.application.cli.builder;

import com.jonwelzel.application.cli.gateway.CLISurveyGateway;
import com.jonwelzel.application.cli.presenter.CLISurveySummaryPresenter;
import com.jonwelzel.core.usecase.GetSurveySummaryUseCase;

public class CLIGetSurveySummaryUseCaseBuilder {
    private CLISurveyGateway gateway;
    private CLISurveySummaryPresenter presenter;

    public CLIGetSurveySummaryUseCaseBuilder() {
        this.gateway = new CLISurveyGateway();
        this.presenter = new CLISurveySummaryPresenter();
    }

    public GetSurveySummaryUseCase getUseCase() {
        return new GetSurveySummaryUseCase(this.gateway, this.presenter);
    }
}

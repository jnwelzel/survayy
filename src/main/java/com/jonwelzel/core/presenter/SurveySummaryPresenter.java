package com.jonwelzel.core.presenter;

import com.jonwelzel.core.model.SurveySummary;

public interface SurveySummaryPresenter {
    void presentSuccess(SurveySummary surveySummary);
    void presentError(String errorMessage);
}

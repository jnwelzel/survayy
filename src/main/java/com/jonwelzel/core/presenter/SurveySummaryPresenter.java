package com.jonwelzel.core.presenter;

import com.jonwelzel.core.pojo.SurveySummary;

public interface SurveySummaryPresenter {
    void presentSuccess(SurveySummary surveySummary);
    void presentError(String errorMessage);
}

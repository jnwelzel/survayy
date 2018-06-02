package com.jonwelzel.application.cli.presenter;

import com.jonwelzel.core.pojo.RatingQuestionAverage;
import com.jonwelzel.core.pojo.SurveySummary;
import com.jonwelzel.core.presenter.SurveySummaryPresenter;
import com.jonwelzel.core.utils.MathUtils;

public class CLISurveySummaryPresenter implements SurveySummaryPresenter {

    public static final String SEPARATOR = "--------------------------------------------------------------------------";

    @Override
    public void presentSuccess(SurveySummary surveySummary) {

        System.out.println("\n");
        System.out.println(SEPARATOR);
        System.out.println("PARTICIPANTS\n" + surveySummary.getTotalParticipantCount());
        System.out.println(SEPARATOR);
        System.out.println("PARTICIPATION\n" + MathUtils.round(surveySummary.getParticipationPercentage(), 2) + "%" );
        System.out.println(SEPARATOR);
        for (RatingQuestionAverage r : surveySummary.getRatingQuestionsAverage()) {
            System.out.println(r.getQuestion().getText());
            System.out.println("AVERAGE: " + MathUtils.round(r.getAverage(), 2));
            System.out.println(SEPARATOR);
        }
    }

    @Override
    public void presentError(String errorMessage) {
        System.out.println(SEPARATOR);
        System.out.println("ERROR\n" + errorMessage);
        System.exit(666);
    }
}

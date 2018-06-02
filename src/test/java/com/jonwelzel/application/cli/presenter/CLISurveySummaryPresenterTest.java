package com.jonwelzel.application.cli.presenter;

import com.jonwelzel.core.pojo.RatingAnswer;
import com.jonwelzel.core.pojo.RatingQuestion;
import com.jonwelzel.core.pojo.RatingQuestionAverage;
import com.jonwelzel.core.pojo.SurveySummary;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CLISurveySummaryPresenterTest {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void should_print_the_survey_summary_when_the_data_provided_is_valid() {
        String expectedOutput =
                "\n\n--------------------------------------------------------------------------\n" +
                "PARTICIPANTS\n" +
                "3\n" +
                "--------------------------------------------------------------------------\n" +
                "PARTICIPATION\n" +
                "75.0%\n" +
                "--------------------------------------------------------------------------\n" +
                "Bar\n" +
                "AVERAGE: 4.0\n" +
                "--------------------------------------------------------------------------\n";
        RatingQuestion ratingQuestion = new RatingQuestion(1L, "Foo", "Bar", new ArrayList<>());
        RatingAnswer ratingAnswer1 = new RatingAnswer(1L, "email1@email.com", 100L, LocalDateTime.now(), ratingQuestion, 5);
        RatingAnswer ratingAnswer2 = new RatingAnswer(1L, "email2@email.com", 101L, LocalDateTime.now(), ratingQuestion, 3);
        ratingQuestion.getAnswers().add(ratingAnswer1);
        ratingQuestion.getAnswers().add(ratingAnswer2);
        List<RatingQuestionAverage> ratingQuestionsAverage = new ArrayList<>();
        ratingQuestionsAverage.add(new RatingQuestionAverage(ratingQuestion, 4D));
        SurveySummary surveySummary = new SurveySummary(75D, 3,
                ratingQuestionsAverage);

        new CLISurveySummaryPresenter().presentSuccess(surveySummary);

        Assertions.assertThat(systemOutRule.getLog()).isEqualTo(expectedOutput);
    }

    @Test
    public void should_print_error_message_when_survey_summary_provided_is_invalid() {
        String errorMessage = "Oops, I did it again.";
        String expectedOutput =
                "--------------------------------------------------------------------------\n" +
                "ERROR\n" +
                "Oops, I did it again.\n";

        new CLISurveySummaryPresenter().presentError(errorMessage);

        Assertions.assertThat(systemOutRule.getLog()).isEqualTo(expectedOutput);
    }
}

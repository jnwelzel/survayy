package com.jonwelzel.core.usecase;

import com.jonwelzel.core.entity.*;
import com.jonwelzel.core.gateway.SurveyGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class GetSurveySummaryUseCaseTest {
    private long SURVEY_ID = 1l;

    @Mock
    private SurveyGateway surveyGateway;

    @Test
    public void itCompilesTheSurveySummaryData() {
        final double participationPercentage = 75d;
        final int totalParticipantCount = 4;
        final int totalResponseCount = 3;
        final double expectedAverageOfFirstRatingQuestion = 4d;
        final double expectedAverageOfSecondRatingQuestion = 4.33;
        final Survey surveyFromGateway = new Survey(SURVEY_ID, generateRatingQuestionsAndAnswers(),
                generateSingleSelectQuestionAndAnswers(), totalParticipantCount, totalResponseCount);
        given(surveyGateway.findById(SURVEY_ID)).willReturn(surveyFromGateway);

        final SurveySummary result = new GetSurveySummaryUseCase(this.surveyGateway).execute(SURVEY_ID);

        assertThat(result.getParticipationPercentage()).isEqualTo(participationPercentage);
        assertThat(result.getTotalParticipantCount()).isEqualTo(totalParticipantCount);

        assertThat(result.getRatingQuestionsAverage().get(0).getAverage()).isEqualTo(expectedAverageOfFirstRatingQuestion);
        assertThat(result.getRatingQuestionsAverage().get(1).getAverage()).isEqualTo(expectedAverageOfSecondRatingQuestion);
    }

    private List<RatingQuestion> generateRatingQuestionsAndAnswers() {
        // First rating question
        RatingQuestion question1 = new RatingQuestion(1l, "The Work", "I like the kind of work I do.",
                new ArrayList<>());
        RatingAnswer question1Answer1 = new RatingAnswer(1l, "employee1@abc.xyz", 1l,
                Calendar.getInstance(), question1, 4);
        RatingAnswer question1Answer2 = new RatingAnswer(2l, "employee2@abc.xyz", 2l, Calendar.getInstance(),
                question1, 3);
        RatingAnswer question1Answer3 = new RatingAnswer(3l, "employee3@abc.xyz", 3l, Calendar.getInstance(),
                question1, 5);
        question1.getAnswers().add(question1Answer1);
        question1.getAnswers().add(question1Answer2);
        question1.getAnswers().add(question1Answer3);

        // Second rating question
        RatingQuestion question2 = new RatingQuestion(2l, "The Place",
                "I feel empowered to get the work done for which I am responsible.", new ArrayList<>());
        RatingAnswer question2Answer1 = new RatingAnswer(4l, "employee1@abc.xyz", 1l,
                Calendar.getInstance(), question2, 5);
        RatingAnswer question2Answer2 = new RatingAnswer(5l, "employee2@abc.xyz", 2l,
                Calendar.getInstance(), question2, 4);
        RatingAnswer question2Answer3 = new RatingAnswer(6l, "employee3@abc.xyz", 3l,
                Calendar.getInstance(), question2, 4);
        question2.getAnswers().add(question2Answer1);
        question2.getAnswers().add(question2Answer2);
        question2.getAnswers().add(question2Answer3);

        List<RatingQuestion> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);

        return questions;
    }

    private List<SingleSelectQuestion> generateSingleSelectQuestionAndAnswers() {
        SingleSelectQuestion question = new SingleSelectQuestion(1l, "Demographics", "Manager",
                new ArrayList<>());
        SingleSelectAnswer answer1 = new SingleSelectAnswer(1l, "employee1@abc.xyz", 1l,
                Calendar.getInstance(), question, "Wade Wilson");
        SingleSelectAnswer answer2 = new SingleSelectAnswer(2l, "employee2@abc.xyz", 2l,
                Calendar.getInstance(), question, "Darth Vader");
        SingleSelectAnswer answer3 = new SingleSelectAnswer(3l, "employee3@abc.xyz", 3l,
                Calendar.getInstance(), question, "Thanos");
        question.getAnswers().add(answer1);
        question.getAnswers().add(answer2);
        question.getAnswers().add(answer3);

        List<SingleSelectQuestion> questions = new ArrayList<>();
        questions.add(question);

        return questions;
    }
}

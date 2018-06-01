package com.jonwelzel.core.usecase;

import com.jonwelzel.core.gateway.survey.SurveyDataParseException;
import com.jonwelzel.core.gateway.survey.SurveyGateway;
import com.jonwelzel.core.pojo.*;
import com.jonwelzel.core.presenter.SurveySummaryPresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.jonwelzel.core.entity.RatingQuestionAverageEntity.getRatingQuestionsAverage;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetSurveySummaryUseCaseTest {
    private final String SURVEY_RAW_DATA = "some random data in string form";
    private final String INVALID_SURVEY_RAW_DATA = "invalid data";

    @Mock
    private SurveyGateway surveyGateway;

    @Mock
    private SurveySummaryPresenter surveySummaryPresenter;

    private Survey surveyWith75PercentParticipation;

    @Before
    public void setUp() {
        // Survey setup
        final long defaultSurveyId = 1L;
        final int defaultTotalResponseCount = 3;
        final int defaultTotalParticipantCount = 4;
        List<RatingQuestion> defaultRatingQuestions = generateRatingQuestionsAndAnswers();
        List<SingleSelectQuestion> defaultSingleSelectQuestions = generateSingleSelectQuestionAndAnswers();
        surveyWith75PercentParticipation = new Survey(defaultSurveyId, defaultRatingQuestions, defaultSingleSelectQuestions,
                defaultTotalParticipantCount, defaultTotalResponseCount);
    }

    @Test
    public void when_the_raw_data_provided_is_valid_the_SurveySummary_should_be_presented() throws SurveyDataParseException {
        given(this.surveyGateway.getSurveyFromRawData(SURVEY_RAW_DATA)).willReturn(surveyWith75PercentParticipation);

        SurveySummary expectedSuveySummary = new SurveySummary(75D, 4,
                getRatingQuestionsAverage(surveyWith75PercentParticipation.getRatingQuestions()));
        new GetSurveySummaryUseCase(this.surveyGateway, this.surveySummaryPresenter).execute(SURVEY_RAW_DATA);

        verify(surveySummaryPresenter).presentSuccess(expectedSuveySummary);
    }

    @Test
    public void when_nobody_participates_in_the_survey_the_SurveySummary_participationPercentage_and_totalParticipantCount_values_should_be_zero() throws SurveyDataParseException {
        surveyWith75PercentParticipation.setTotalParticipantCount(0);
        given(this.surveyGateway.getSurveyFromRawData(SURVEY_RAW_DATA)).willReturn(surveyWith75PercentParticipation);

        SurveySummary expectedSurveySummary = new SurveySummary(0, 0,
                getRatingQuestionsAverage(surveyWith75PercentParticipation.getRatingQuestions()));
        new GetSurveySummaryUseCase(this.surveyGateway, this.surveySummaryPresenter).execute(SURVEY_RAW_DATA);

        verify(surveySummaryPresenter).presentSuccess(expectedSurveySummary);
    }

    @Test
    public void when_the_raw_data_provided_is_invalid_the_error_should_be_presented() throws SurveyDataParseException {
        final String errorMessage = "Something is wrong with the survey data";
        given(surveyGateway.getSurveyFromRawData(INVALID_SURVEY_RAW_DATA)).willThrow(new SurveyDataParseException(errorMessage));

        new GetSurveySummaryUseCase(this.surveyGateway, this.surveySummaryPresenter).execute(INVALID_SURVEY_RAW_DATA);

        verify(surveySummaryPresenter).presentError(errorMessage);
    }

    private List<RatingQuestion> generateRatingQuestionsAndAnswers() {
        // First rating question
        RatingQuestion question1 = new RatingQuestion(1l, "The Work", "I like the kind of work I do.",
                new ArrayList<>());
        RatingAnswer question1Answer1 = new RatingAnswer(1l, "employee1@abc.xyz", 1l,
                LocalDateTime.now(), question1, 4);
        RatingAnswer question1Answer2 = new RatingAnswer(2l, "employee2@abc.xyz", 2l, LocalDateTime.now(),
                question1, 3);
        RatingAnswer question1Answer3 = new RatingAnswer(3l, "employee3@abc.xyz", 3l, LocalDateTime.now(),
                question1, 5);
        question1.getAnswers().add(question1Answer1);
        question1.getAnswers().add(question1Answer2);
        question1.getAnswers().add(question1Answer3);

        // Second rating question
        RatingQuestion question2 = new RatingQuestion(2l, "The Place",
                "I feel empowered to get the work done for which I am responsible.", new ArrayList<>());
        RatingAnswer question2Answer1 = new RatingAnswer(4l, "employee1@abc.xyz", 1l,
                LocalDateTime.now(), question2, 5);
        RatingAnswer question2Answer2 = new RatingAnswer(5l, "employee2@abc.xyz", 2l,
                LocalDateTime.now(), question2, 4);
        RatingAnswer question2Answer3 = new RatingAnswer(6l, "employee3@abc.xyz", 3l,
                LocalDateTime.now(), question2, 4);
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
                LocalDateTime.now(), question, "Wade Wilson");
        SingleSelectAnswer answer2 = new SingleSelectAnswer(2l, "employee2@abc.xyz", 2l,
                LocalDateTime.now(), question, "Darth Vader");
        SingleSelectAnswer answer3 = new SingleSelectAnswer(3l, "employee3@abc.xyz", 3l,
                LocalDateTime.now(), question, "Thanos");
        question.getAnswers().add(answer1);
        question.getAnswers().add(answer2);
        question.getAnswers().add(answer3);

        List<SingleSelectQuestion> questions = new ArrayList<>();
        questions.add(question);

        return questions;
    }
}

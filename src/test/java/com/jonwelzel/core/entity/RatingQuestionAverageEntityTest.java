package com.jonwelzel.core.entity;

import com.jonwelzel.core.pojo.RatingAnswer;
import com.jonwelzel.core.pojo.RatingQuestion;
import com.jonwelzel.core.pojo.RatingQuestionAverage;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RatingQuestionAverageEntityTest {
    private List<RatingQuestion> questions;

    @Before
    public void setUp() {
        RatingQuestion ratingQuestion = new RatingQuestion(1L, "Ayy", "Lmao", new ArrayList<>());
        RatingAnswer ratingAnswer1 = new RatingAnswer(1L, "email1@email.com", 1L, LocalDateTime.now(), ratingQuestion, 4);
        RatingAnswer ratingAnswer2 = new RatingAnswer(2L, "email2@email.com", 2L, LocalDateTime.now(), ratingQuestion, 5);
        ratingQuestion.getAnswers().add(ratingAnswer1);
        ratingQuestion.getAnswers().add(ratingAnswer2);

        questions = new ArrayList<>();
        questions.add(ratingQuestion);
    }

    @Test
    public void when_question_list_is_null_should_return_empty_list() {
        List<RatingQuestionAverage> result = RatingQuestionAverageEntity.getRatingQuestionsAverage(null);

        Assertions.assertThat(result.isEmpty()).isTrue();
    }

    @Test
    public void when_question_list_is_empty_should_return_empty_list() {
        List<RatingQuestionAverage> result = RatingQuestionAverageEntity.getRatingQuestionsAverage(new ArrayList<>());

        Assertions.assertThat(result.isEmpty()).isTrue();
    }

    @Test
    public void when_answer_list_is_empty_should_return_zero() {
        double expectedAverageValueForQuestion = 0D;
        questions.get(0).setAnswers(new ArrayList<>());

        List<RatingQuestionAverage> result = RatingQuestionAverageEntity.getRatingQuestionsAverage(questions);

        Assertions.assertThat(result.get(0).getAverage()).isEqualTo(expectedAverageValueForQuestion);
    }

    @Test
    public void only_answers_with_submission_date_should_count_in_average_value() {
        double expectedAverageValueForQuestion = 4.5;

        List<RatingQuestionAverage> result = RatingQuestionAverageEntity.getRatingQuestionsAverage(questions);

        Assertions.assertThat(result.get(0).getAverage()).isEqualTo(expectedAverageValueForQuestion);
    }

    @Test
    public void answers_without_submission_date_should_not_count_in_average_value() {
        int expectedAverageValueForQuestion = 5;
        questions.get(0).getAnswers().get(0).setSubmittedAt(null);

        List<RatingQuestionAverage> result = RatingQuestionAverageEntity.getRatingQuestionsAverage(questions);

        Assertions.assertThat(result.get(0).getAverage()).isEqualTo(expectedAverageValueForQuestion);
    }
}

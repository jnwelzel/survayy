/*
 * The MIT License
 *
 * Copyright 2018 jwelzel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.jonwelzel.core.usecase;

import com.jonwelzel.core.entity.RatingQuestionAnswer;
import com.jonwelzel.core.gateway.RatingQuestionAnswerGateway;
import com.jonwelzel.core.usecase.ratingquestion.GetAverageScoreUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

/**
 *
 * @author jwelzel
 */
@RunWith(MockitoJUnitRunner.class)
public class GetAverageScoreUsecaseTest {
    
    @Mock
    private RatingQuestionAnswerGateway ratingQuestionAnswerGateway;
    
    private final long QUESTION_ID = 1L;
    
    @Test
    public void calculatesTheAverageScoreOfGivenRatingQuestion() {
        final int answersCount = 3;
        final int ratingValueForAnswers = 4;
        final List<RatingQuestionAnswer> answers = generateAnswers(answersCount, ratingValueForAnswers);
        given(ratingQuestionAnswerGateway.getAnswersByQuestion(QUESTION_ID)).willReturn(answers);
        
        GetAverageScoreUsecase usecase = new GetAverageScoreUsecase(ratingQuestionAnswerGateway);
        final double result = usecase.execute(QUESTION_ID);
        
        assertThat(result).isEqualTo(ratingValueForAnswers);
    }
    
    @Test
    public void returnsZeroWhenThereAreNoAnswersForQuestion() {}
    
    private List<RatingQuestionAnswer> generateAnswers(int count, int answersRatingValue) {
        List<RatingQuestionAnswer> answers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            answers.add(
                new RatingQuestionAnswer(
                    "email" + i + "@email.com",
                    QUESTION_ID,
                    Calendar.getInstance(),
                    answersRatingValue
                )
            );
        }
        return answers;
    }
}

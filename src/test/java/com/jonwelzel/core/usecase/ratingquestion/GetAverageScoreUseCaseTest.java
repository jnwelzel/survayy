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
package com.jonwelzel.core.usecase.ratingquestion;

import com.jonwelzel.core.entity.RatingAnswer;
import com.jonwelzel.core.entity.RatingQuestion;
import com.jonwelzel.core.gateway.RatingAnswerGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 *
 * @author jwelzel
 */
@RunWith(MockitoJUnitRunner.class)
public class GetAverageScoreUseCaseTest {
    
    @Mock
    private RatingAnswerGateway ratingAnswerGateway;

    @Mock
    private RatingQuestion ratingQuestion;
    
    private final long QUESTION_ID = 1L;
    
    @Test
    public void calculatesTheAverageScoreOfGivenRatingQuestion() {
        final int answersCount = 3;
        final int ratingValueForAnswers = 4;
        final List<RatingAnswer> answers = generateAnswers(answersCount, ratingValueForAnswers);
        given(ratingAnswerGateway.getAnswersByQuestion(QUESTION_ID)).willReturn(answers);
        
        GetAverageScoreUseCase usecase = new GetAverageScoreUseCase(ratingAnswerGateway);
        final double result = usecase.execute(QUESTION_ID);
        
        assertThat(result).isEqualTo(ratingValueForAnswers);
    }
    
    @Test
    public void returnsZeroWhenThereAreNoAnswersForQuestion() {
        List<RatingAnswer> emptyAnswerList = new ArrayList<>();
        given(ratingAnswerGateway.getAnswersByQuestion(QUESTION_ID)).willReturn(emptyAnswerList);

        GetAverageScoreUseCase usecase = new GetAverageScoreUseCase(ratingAnswerGateway);
        final double result = usecase.execute(QUESTION_ID);

        assertThat(result).isEqualTo(0d);
    }
    
    private List<RatingAnswer> generateAnswers(int count, int answersRatingValue) {
        List<RatingAnswer> answers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            answers.add(new RatingAnswer(i, "email" + i + "@email.com", i, Calendar.getInstance(), ratingQuestion,
                    answersRatingValue));
        }
        return answers;
    }
}

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

import com.jonwelzel.core.entity.RatingQuestionAnswer;
import com.jonwelzel.core.gateway.RatingQuestionAnswerGateway;

import java.util.List;

/**
 * Get the average score of a rating question.
 * @author jwelzel
 */
public class GetAverageScoreUseCase {
    private final RatingQuestionAnswerGateway ratingQuestionAnswerGateway;

    public GetAverageScoreUseCase(RatingQuestionAnswerGateway answerGateway) {
        this.ratingQuestionAnswerGateway = answerGateway;
    }
    
    public double execute(long questionId) {
        final List<RatingQuestionAnswer> answers = this.ratingQuestionAnswerGateway.getAnswersByQuestion(questionId);
        
        return calculateAverageScore(answers);
    }
    
    private double calculateAverageScore(List<RatingQuestionAnswer> answers) {
        if (answers.isEmpty()) {
            return 0d;
        }
        
        int scoreSum = 0;
        scoreSum = answers.stream().map((answer) -> answer.getValue()).reduce(scoreSum, Integer::sum);
        
        return scoreSum / answers.size();
    }
}

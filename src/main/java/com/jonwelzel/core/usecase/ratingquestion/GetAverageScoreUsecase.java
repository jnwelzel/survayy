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

import com.jonwelzel.core.gateway.QuestionGateway;
import com.jonwelzel.core.usecase.QuestionNotFoundException;
import com.jonwelzel.core.usecase.UnsupportedQuestionTypeException;
import com.jonwelzel.entity.Question;
import com.jonwelzel.entity.QuestionType;

/**
 * Get the average score for a survey rating question.
 * @author jwelzel
 */
public class GetAverageScoreUsecase {
    private QuestionGateway questionGateway;

    public GetAverageScoreUsecase(QuestionGateway questionGateway) {
        this.questionGateway = questionGateway;
    }
    
    public double execute(long questionId) {
        Question question = this.questionGateway.getQuestion(questionId);
        failIfQuestionDoesNotExist(question);
        failIfQuestionIsNotRatingType(question);
        
        return 0.00;
    }
    
    private void failIfQuestionDoesNotExist(Question question) {
        if (question == null) {
            throw new QuestionNotFoundException();
        }      
    }

    private void failIfQuestionIsNotRatingType(Question question) {
        if (question.getQuestionType() != QuestionType.RATING_QUESTION) {
            throw new UnsupportedQuestionTypeException();
        }
    }
}

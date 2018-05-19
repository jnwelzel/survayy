package com.jonwelzel.core.gateway;

import com.jonwelzel.core.entity.RatingAnswer;

import java.util.List;

/**
 *
 * @author jwelzel
 */
public interface RatingAnswerGateway {
    List<RatingAnswer> getAnswersByQuestion(long questionId);
}

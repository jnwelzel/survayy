package com.jonwelzel.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class RatingQuestion extends Question {

    @Getter
    @Setter
    private List<RatingAnswer> answers;

    public RatingQuestion(long id, String theme, String text, List<RatingAnswer> answers) {
        super(id, theme, text);
        this.answers = answers;
    }
}

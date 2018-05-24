package com.jonwelzel.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SingleSelectQuestion extends Question {

    @Getter
    @Setter
    private List<SingleSelectAnswer> answers;

    public SingleSelectQuestion(long id, String theme, String text, List<SingleSelectAnswer> answers) {
        super(id, theme, text);
        this.answers = answers;
    }
}

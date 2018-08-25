package com.jonwelzel.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SingleSelectQuestion extends Question {

    @Getter
    @Setter
    private List<SingleSelectAnswer> answers;

    public SingleSelectQuestion(Long id, String theme, String text, List<SingleSelectAnswer> answers) {
        super(id, theme, text);
        this.answers = answers;
    }
}

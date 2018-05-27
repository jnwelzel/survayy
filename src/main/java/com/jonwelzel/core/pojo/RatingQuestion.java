package com.jonwelzel.core.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RatingQuestion extends Question {

    @Getter
    @Setter
    private List<RatingAnswer> answers;

    public RatingQuestion(long id, String theme, String text, List<RatingAnswer> answers) {
        super(id, theme, text);
        this.answers = answers;
    }
}

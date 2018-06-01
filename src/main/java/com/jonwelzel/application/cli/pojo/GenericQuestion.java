package com.jonwelzel.application.cli.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@EqualsAndHashCode
public class GenericQuestion {

    @Getter
    @Setter
    private String theme;

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private List<GenericAnswer> answers;

    @Getter
    @Setter
    private QuestionType questionType;

    public GenericQuestion(String theme, String text, QuestionType questionType, List<GenericAnswer> answers) {
        this.theme = theme;
        this.text = text;
        this.questionType = questionType;
        this.answers = answers;
    }
}

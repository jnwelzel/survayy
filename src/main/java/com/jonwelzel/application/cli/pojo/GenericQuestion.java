package com.jonwelzel.application.cli.pojo;

import com.jonwelzel.core.pojo.Question;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GenericQuestion extends Question {

    @Getter
    @Setter
    private List<GenericAnswer> answers;

    @Getter
    @Setter
    private QuestionType questionType;

    public GenericQuestion(Long id, String theme, String text, QuestionType questionType, List<GenericAnswer> answers) {
        super(id, theme, text);
        this.questionType = questionType;
        this.answers = answers;
    }
}

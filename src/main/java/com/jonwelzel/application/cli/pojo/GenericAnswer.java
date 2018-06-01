package com.jonwelzel.application.cli.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class GenericAnswer {

    @Getter
    @Setter
    private String emailValue;

    @Getter
    @Setter
    private String employeeIdValue;

    @Getter
    @Setter
    private String submittedAtValue;

    @Getter
    @Setter
    private String answerValue;

    @Getter
    @Setter
    private QuestionType questionType;

    public GenericAnswer(String emailValue, String employeeIdValue, String submittedAtValue, String answerValue,
                         QuestionType questionType) {
        this.emailValue = emailValue;
        this.employeeIdValue = employeeIdValue;
        this.submittedAtValue = submittedAtValue;
        this.answerValue = answerValue;
        this.questionType = questionType;
    }
}

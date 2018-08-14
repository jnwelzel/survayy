package com.jonwelzel.application.cli.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class QuestionHeaderPositions {

    @Getter
    @Setter
    private Integer type;

    @Getter
    @Setter
    private Integer theme;

    @Getter
    @Setter
    private Integer text;

    public QuestionHeaderPositions(Integer type, Integer theme, Integer text) {
        this.type = type;
        this.theme = theme;
        this.text = text;
    }
}

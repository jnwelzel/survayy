package com.jonwelzel.core.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public abstract class Question {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String theme;

    @Getter
    @Setter
    private String text;

    public Question(Long id, String theme, String text) {
        this.id = id;
        this.theme = theme;
        this.text = text;
    }
}

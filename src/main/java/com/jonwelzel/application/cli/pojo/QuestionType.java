package com.jonwelzel.application.cli.pojo;

import lombok.Getter;
import lombok.Setter;

public enum QuestionType {
    RATING("ratingquestion"),
    SINGLE_SELECT("singleselect");

    @Getter
    @Setter
    private String type;

    QuestionType(String type) {
        this.type = type;
    }

    public static QuestionType byString(String type) {
        QuestionType[] types = QuestionType.values();
        for (QuestionType q : types) {
            if (q.getType().equals(type)) {
                return q;
            }
        }

        return null;
    }
}

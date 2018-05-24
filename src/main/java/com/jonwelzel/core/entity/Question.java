package com.jonwelzel.core.entity;

/**
 *
 * @author jwelzel
 */
public abstract class Question {
    private long id;
    private String theme;
    private String text;

    public Question(long id, String theme, String text) {
        this.id = id;
        this.theme = theme;
        this.text = text;
    }
}

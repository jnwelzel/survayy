package com.jonwelzel.core.entity;

import java.time.LocalDateTime;

/**
 *
 * @author jwelzel
 */
public abstract class Answer {
    private long id;
    private String email;
    private long employeeId;
    private LocalDateTime submittedAt;

    public Answer(long id, String email, long employeeId, LocalDateTime submittedAt) {
        this.id = id;
        this.email = email;
        this.employeeId = employeeId;
        this.submittedAt = submittedAt;
    }
}

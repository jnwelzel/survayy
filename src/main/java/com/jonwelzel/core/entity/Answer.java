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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}

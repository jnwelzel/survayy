package com.jonwelzel.core.entity;

import java.util.Calendar;

/**
 *
 * @author jwelzel
 */
public abstract class Answer {
    private long id;
    private String email;
    private long employeeId;
    private Calendar submittedAt;

    public Answer(long id, String email, long employeeId, Calendar submittedAt) {
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

    public Calendar getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Calendar submittedAt) {
        this.submittedAt = submittedAt;
    }
}

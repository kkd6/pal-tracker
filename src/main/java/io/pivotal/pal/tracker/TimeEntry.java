package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.Objects;

public class TimeEntry {

    private Long id;
    private Long projectId;
    private Long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry(Long projectId, Long userId, LocalDate ldate, int hours) {
        this.projectId = projectId;
        this.userId = userId;
        this.date = ldate;
        this.hours = hours;
    }

    public TimeEntry(Long id, Long projectId, Long userId, LocalDate ldate, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = ldate;
        this.hours = hours;

    }

    public TimeEntry() {

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntry timeEntry = (TimeEntry) o;
        return hours == timeEntry.hours &&
                Objects.equals(id, timeEntry.id) &&
                Objects.equals(projectId, timeEntry.projectId) &&
                Objects.equals(userId, timeEntry.userId) &&
                Objects.equals(date, timeEntry.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, projectId, userId, date, hours);
    }

    public Long getId() {
        return id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}

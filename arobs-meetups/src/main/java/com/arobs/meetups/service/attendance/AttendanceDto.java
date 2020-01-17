package com.arobs.meetups.service.attendance;

import com.arobs.meetups.entities.Event;
import com.arobs.meetups.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AttendanceDto {

    @JsonProperty("attendance_id")
    private int id;

    @JsonProperty("attendee")
    private User user;

    @JsonProperty("event")
    private Event event;

    @JsonProperty("note")
    private int note;

    @JsonProperty("comment")
    private String comment;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

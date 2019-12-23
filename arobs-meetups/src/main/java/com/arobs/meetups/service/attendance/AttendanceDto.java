package com.arobs.meetups.service.attendance;

import com.arobs.meetups.entities.Event;
import com.arobs.meetups.entities.User;

public class AttendanceDto {

    private User user;
    private Event event;
    private int note;
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
}

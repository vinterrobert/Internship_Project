package com.arobs.meetups.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "attendance", uniqueConstraints=
@UniqueConstraint(columnNames={"user_id", "event_id"}))
public class Attendance {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "attendance_id", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "note", nullable = false)
    @Min(value = 0, message = "Lowest note is 0")
    @Max(value = 5, message = "Highest note is 5")
    private int note;

    @Column(name = "comment", nullable = false, length = 500)
    private String comment;

    public Attendance(){}

    public Attendance(User user, Event event){
        this.user = user;
        this.event = event;
        this.comment = " ";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

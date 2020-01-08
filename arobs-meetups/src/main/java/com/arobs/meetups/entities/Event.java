package com.arobs.meetups.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "difficulty", nullable = false)
    private String difficulty;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "duration_min", nullable = false)
    @Min(value = 0, message = "Duration must have a positive value")
    private int durationInMinutes;

    @Column(name = "max_people", nullable = false)
    @Min(value = 0, message = "People number must be a positive value")
    private int maximumPeople;

    @Column(name = "event_date", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp date;

    @Column(name = "room", nullable = false)
    private String room;

    @OneToMany(
            mappedBy = "event",
            cascade = CascadeType.ALL
    )
    Set<Attendance> attendees = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getMaximumPeople() {
        return maximumPeople;
    }

    public void setMaximumPeople(int maximumPeople) {
        this.maximumPeople = maximumPeople;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

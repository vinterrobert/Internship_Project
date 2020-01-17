package com.arobs.meetups.service.event;

import com.arobs.meetups.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class EventDto {

    @JsonProperty("event_id")
    private int id;

    @JsonProperty("presenter")
    private User user;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("difficulty")
    private String difficulty;

    @JsonProperty("language")
    private String language;

    @JsonProperty("duration_in_minutes")
    private int durationInMinutes;

    @JsonProperty("maximum_number_of_people")
    private int maximumPeople;

    @JsonProperty("event_date")
    private String date;

    @JsonProperty("room")
    private String room;

    @JsonProperty("closed")
    private boolean closed = false;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public boolean isClosed() {
        return closed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}

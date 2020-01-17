package com.arobs.meetups.service.awardinghistory;

import com.arobs.meetups.entities.Prize;
import com.arobs.meetups.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class AwardingHistoryDto {

    @JsonProperty("awarding_id")
    private int id;

    @JsonProperty("user")
    private User user;

    @JsonProperty("awarding_date")
    private Date awardingDate;

    @JsonProperty("number_of_points")
    private int points;

    @JsonProperty("prize")
    private Prize prize;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getAwardingDate() {
        return awardingDate;
    }

    public void setAwardingDate(Date awardingDate) {
        this.awardingDate = awardingDate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

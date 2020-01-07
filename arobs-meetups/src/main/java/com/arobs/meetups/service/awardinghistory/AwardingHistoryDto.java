package com.arobs.meetups.service.awardinghistory;

import com.arobs.meetups.entities.Prize;
import com.arobs.meetups.entities.User;

import java.sql.Date;

public class AwardingHistoryDto {

    private User user;
    private Date awardingDate;
    private int points;
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
}

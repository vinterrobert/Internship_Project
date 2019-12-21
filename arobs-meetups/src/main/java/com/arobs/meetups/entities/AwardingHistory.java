package com.arobs.meetups.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "awarding_history")
public class AwardingHistory {

    @Id
    @GeneratedValue
    @Column(name = "awarding_id", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "awarding_date", nullable = false)
    private Date awardingDate;

    @Column(name = "number_of_points", nullable = false)
    private int points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prize_id", nullable = false)
    private Prize prize;

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

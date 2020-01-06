package com.arobs.meetups.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "points")
    private int points;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "vote",
            joinColumns = {@JoinColumn(name = "id_user", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "id_proposal", referencedColumnName = "proposal_id")}
    )
    @JsonIgnore
    Set<Proposal> votedProposals = new HashSet<>();

    public Set<Proposal> getVotedProposals() {
        return votedProposals;
    }

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
           )
    Set<Attendance> eventsAttended = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    Set<Attendance> proposalsCreated = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    Set<Attendance> eventsCreated = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    Set<AwardingHistory> awards = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }



}

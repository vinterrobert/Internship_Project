package com.arobs.meetups.service.vote;

import com.arobs.meetups.entities.Proposal;
import com.arobs.meetups.entities.User;

public class VoteDto {

    User user;
    Proposal proposal;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }
}

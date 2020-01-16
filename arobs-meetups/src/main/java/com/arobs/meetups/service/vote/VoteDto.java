package com.arobs.meetups.service.vote;

import com.arobs.meetups.entities.Proposal;
import com.arobs.meetups.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteDto {

    @JsonProperty("voter")
    User user;

    @JsonProperty("proposal")
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

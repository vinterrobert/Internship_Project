package com.arobs.meetups.repositories;

import com.arobs.meetups.entities.Proposal;

public class VotedProposal {

    private int idProposal;
    private String proposalName;
    private int numberOfVotes;

    public VotedProposal(int idProposal, String proposalName, int numberOfVotes) {
        this.idProposal = idProposal;
        this.proposalName = proposalName;
        this.numberOfVotes = numberOfVotes;
    }

    public int getIdProposal() {
        return idProposal;
    }

    public void setIdProposal(int idProposal) {
        this.idProposal = idProposal;
    }

    public String getProposalName() {
        return proposalName;
    }

    public void setProposalName(String proposalName) {
        this.proposalName = proposalName;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }
}

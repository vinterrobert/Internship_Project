package com.arobs.meetups.service.vote;

import com.arobs.meetups.entities.Proposal;
import com.arobs.meetups.entities.User;
import com.arobs.meetups.repositories.VotedProposal;
import com.arobs.meetups.service.proposal.ProposalDto;
import com.arobs.meetups.service.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class VoteServiceImplementation implements IVoteService{

    @Autowired
    VoteObject voteObject;

    @Override
    @Transactional
    public Set<ProposalDto> allVotedProposalsOfAnUser(int idUser) {
        return voteObject.allVotedProposalsOfAnUser(idUser);
    }

    @Override
    @Transactional
    public void createVote(int idUser, int idProposal) {
        voteObject.createVote(idUser, idProposal);
    }

    @Override
    @Transactional
    public Set<UserDto> getAllVotesOfAProposal(int idProposal) {
        return voteObject.getAllVotesOfAProposal(idProposal);
    }

    @Override
    @Transactional
    public void deleteVote(int idUser, int idProposal) {
        voteObject.deleteVote(idUser, idProposal);
    }

    @Override
    @Transactional
    public List<VotedProposal> getTopVotedProposals() {
        return voteObject.getTopVotedProposals();
    }
}

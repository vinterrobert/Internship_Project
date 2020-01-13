package com.arobs.meetups.service.vote;

import com.arobs.meetups.repositories.VotedProposal;
import com.arobs.meetups.service.proposal.ProposalDto;
import com.arobs.meetups.service.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
public class VoteServiceImplementation implements VoteService {

    @Autowired
    VoteObject voteObject;

    @Override
    @Transactional
    public Set<ProposalDto> allVotedProposalsOfAnUser(int idUser) {
        return voteObject.allVotedProposalsOfAnUser(idUser);
    }

    @Override
    @Transactional
    public void create(int idUser, int idProposal) {
        voteObject.create(idUser, idProposal);
    }

    @Override
    @Transactional
    public Set<UserDto> getAllOfAProposal(int idProposal) {
        return voteObject.getAllOfAProposal(idProposal);
    }

    @Override
    @Transactional
    public void delete(int idUser, int idProposal) {
        voteObject.delete(idUser, idProposal);
    }

    @Override
    @Transactional
    public List<VotedProposal> getTopVotedProposals() {
        return voteObject.getTopVotedProposals();
    }
}

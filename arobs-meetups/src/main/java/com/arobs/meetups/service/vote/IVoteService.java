package com.arobs.meetups.service.vote;

import com.arobs.meetups.repositories.VotedProposal;
import com.arobs.meetups.service.proposal.ProposalDto;
import com.arobs.meetups.service.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface IVoteService {

    Set<ProposalDto> allVotedProposalsOfAnUser(int idUser);
    void createVote(int idUser, int idProposal);
    Set<UserDto> getAllVotesOfAProposal(int idProposal);
    void deleteVote(int idUser, int idProposal);
    List<VotedProposal> getTopVotedProposals();
}

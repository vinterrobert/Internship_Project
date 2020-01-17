package com.arobs.meetups.service.vote;

import com.arobs.meetups.repositories.VotedProposal;
import com.arobs.meetups.service.proposal.ProposalDto;
import com.arobs.meetups.service.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface VoteService {
    Set<ProposalDto> allVotedProposalsOfAnUser(int idUser);
    Set<UserDto> getAllOfAProposal(int idProposal) throws Exception;
    List<VotedProposal> getTopVotedProposals();
    void create(int idUser, int idProposal) throws Exception;
    void delete(int idUser, int idProposal);
}

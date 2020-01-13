package com.arobs.meetups.service.vote;

import com.arobs.meetups.entities.Proposal;
import com.arobs.meetups.entities.User;
import com.arobs.meetups.repositories.UserRepository;
import com.arobs.meetups.repositories.VoteRepository;
import com.arobs.meetups.repositories.VotedProposal;
import com.arobs.meetups.service.proposal.ProposalDto;
import com.arobs.meetups.service.proposal.ProposalMapper;
import com.arobs.meetups.service.user.UserDto;
import com.arobs.meetups.service.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class VoteObject {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProposalMapper proposalMapper;

    @Autowired
    UserMapper userMapper;

    public Set<ProposalDto> allVotedProposalsOfAnUser(int idUser){
        Set<Proposal> votedProposals = voteRepository.getAllOfAnUser(idUser);
        Set<ProposalDto> votedProposalsDto = new HashSet<>();
        if(!votedProposals.isEmpty()){
            for(Proposal proposal : votedProposals){
                votedProposalsDto.add(proposalMapper.map(proposal, ProposalDto.class));
            }
        }
        return votedProposalsDto;
    }

    public void create(int idUser, int idProposal){
        User voter = userRepository.findById(idUser);
        int userCurrentPoints = voter.getPoints();
        voteRepository.create(idUser, idProposal);
        voter.setPoints(userCurrentPoints + 1);
        userRepository.update(voter);
    }

    public Set<UserDto> getAllOfAProposal(int idProposal){
        Set<User> voters = voteRepository.getAllOfAProposal(idProposal);
        Set<UserDto> votersDto = new HashSet<>();
        if(!voters.isEmpty()){
            for(User user : voters){
                votersDto.add(userMapper.map(user, UserDto.class));
            }
        }
        return votersDto;
    }

    public void delete(int idUser, int idProposal){
        User voter = userRepository.findById(idUser);
        voter.setPoints(voter.getPoints() - 1);
        userRepository.update(voter);
        voteRepository.delete(idUser, idProposal);
    }

    public List<VotedProposal> getTopVotedProposals(){
        return voteRepository.getTopVotedProposals();
    }
}

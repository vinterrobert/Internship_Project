package com.arobs.meetups.controllers;

import com.arobs.meetups.repositories.ProposalRepository;
import com.arobs.meetups.repositories.UserRepository;
import com.arobs.meetups.repositories.VoteRepository;
import com.arobs.meetups.service.proposal.ProposalDto;
import com.arobs.meetups.service.user.UserDto;
import com.arobs.meetups.service.vote.IVoteService;
import com.arobs.meetups.repositories.VotedProposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    IVoteService voteService;

    @PostMapping(path = "/createVote/user{idUser}/proposal{idProposal}")
    public ResponseEntity<String> createVote(@PathVariable int idUser, @PathVariable int idProposal) {
        voteService.createVote(idUser, idProposal);
        return ResponseEntity.ok("Vote created");
    }

    @GetMapping(path = "/getAllVotedProposals/user{idUser}")
    public ResponseEntity<Set<ProposalDto>> getAllVotedProposals(@PathVariable int idUser){
        return ResponseEntity.ok(voteService.allVotedProposalsOfAnUser(idUser));
    }

    @GetMapping(path = "/getAllVotesOfAProposal/proposal{idProposal}")
    public ResponseEntity<Set<UserDto>> getAllVotesOfAProposal(@PathVariable int idProposal){
        return ResponseEntity.ok(voteService.getAllVotesOfAProposal(idProposal));
    }

    @DeleteMapping(path = "/deleteVote/user{idUser}/proposal{idProposal}")
    public ResponseEntity<String> deleteVote(@PathVariable int idUser, @PathVariable int idProposal){
        voteService.deleteVote(idUser, idProposal);
        return ResponseEntity.ok("Vote Deleted");
    }

    @GetMapping(path = "/getTopVotedProposals")
    public ResponseEntity<List<VotedProposal>> getTopVotedProposals(){
        return ResponseEntity.ok(voteService.getTopVotedProposals());
    }

}

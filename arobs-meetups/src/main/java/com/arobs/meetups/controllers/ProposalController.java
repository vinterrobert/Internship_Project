package com.arobs.meetups.controllers;

import com.arobs.meetups.service.proposal.IProposalService;
import com.arobs.meetups.service.proposal.ProposalDto;
import com.arobs.meetups.service.proposal.ProposalServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    IProposalService proposalService;

    @GetMapping(path = "/id{idProposal}")
    public ResponseEntity<ProposalDto> findById(@PathVariable int idProposal) throws ClassNotFoundException {
        return ResponseEntity.ok(proposalService.findById(idProposal));
    }

    @GetMapping (path = "/allProposals")
    public ResponseEntity<List<ProposalDto>> getAllProposals () throws ClassNotFoundException{
        return ResponseEntity.ok(proposalService.getAllProposals());
    }

    @GetMapping (path = "/difficulty{difficulty}")
    public ResponseEntity<List<ProposalDto>> getAllWithSameDifficulty (@PathVariable String difficulty) throws ClassNotFoundException{
        return ResponseEntity.ok(proposalService.getAllWithSameDifficulty(difficulty));
    }

    @GetMapping (path = "/type{type}")
    public ResponseEntity<List<ProposalDto>> getAllWithSameType (@PathVariable String type) throws ClassNotFoundException{
        return ResponseEntity.ok(proposalService.getAllWithSameType(type));
    }

    @GetMapping (path = "/language{language}")
    public ResponseEntity<List<ProposalDto>> getAllWithSameLanguage (@PathVariable String language) throws ClassNotFoundException{
        return ResponseEntity.ok(proposalService.getAllWithSameType(language));
    }

    @GetMapping (path = "/user{id}")
    public ResponseEntity<List<ProposalDto>> getAllOfAnUser (@PathVariable int id) throws ClassNotFoundException{
        return ResponseEntity.ok(proposalService.getAllOfAnUser(id));
    }

    @PostMapping(path = "/createProposal")
    public ResponseEntity<String> createProposal(@RequestBody ProposalDto newProposalDto) {
        proposalService.createProposal(newProposalDto);
        return ResponseEntity.ok("Proposal created");
    }

    @PutMapping(path = "/updateProposal{id}")
    public ResponseEntity<String> updateProposal (@RequestBody ProposalDto updatedProposalDto, @PathVariable int id){
        proposalService.updateProposal(id, updatedProposalDto);
        return ResponseEntity.ok("Proposal updated");
    }

    @DeleteMapping(path = "/deleteProposal{id}")
    public ResponseEntity<String> deleteProposal (@PathVariable int id){
        proposalService.deleteProposal(id);
        return ResponseEntity.ok("Proposal deleted");
    }

}

package com.arobs.meetups.controllers;

import com.arobs.meetups.service.proposal.ProposalService;
import com.arobs.meetups.service.proposal.ProposalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    ProposalService proposalService;

    @GetMapping(path = "/id{idProposal}")
    public ResponseEntity<ProposalDto> findProposalById(@PathVariable int idProposal) throws ClassNotFoundException {
        return ResponseEntity.ok(proposalService.findById(idProposal));
    }

    @GetMapping (path = "/allProposals")
    public ResponseEntity<List<ProposalDto>> getAllProposals () throws ClassNotFoundException{
        return ResponseEntity.ok(proposalService.getAll());
    }

    @GetMapping (path = "/difficulty{difficulty}")
    public ResponseEntity<List<ProposalDto>> getAllProposalsWithSameDifficulty (@PathVariable String difficulty) throws ClassNotFoundException{
        return ResponseEntity.ok(proposalService.getAllWithSameDifficulty(difficulty));
    }

    @GetMapping (path = "/type{type}")
    public ResponseEntity<List<ProposalDto>> getAllProposalsWithSameType (@PathVariable String type) throws ClassNotFoundException{
        return ResponseEntity.ok(proposalService.getAllWithSameType(type));
    }

    @GetMapping (path = "/language{language}")
    public ResponseEntity<List<ProposalDto>> getAllProposalsWithSameLanguage (@PathVariable String language) throws ClassNotFoundException{
        return ResponseEntity.ok(proposalService.getAllWithSameLanguage(language));
    }

    @GetMapping (path = "/user{id}")
    public ResponseEntity<List<ProposalDto>> getAllProposalsOfAnUser (@PathVariable int id) throws ClassNotFoundException{
        return ResponseEntity.ok(proposalService.getAllOfAnUser(id));
    }

    @PostMapping(path = "/createProposal")
    public ResponseEntity<String> createProposal(@RequestBody ProposalDto newProposalDto) {
        proposalService.create(newProposalDto);
        return ResponseEntity.ok("Proposal created");
    }

    @PutMapping(path = "/updateProposal{id}")
    public ResponseEntity<String> updateProposal (@RequestBody ProposalDto updatedProposalDto, @PathVariable int id){
        proposalService.update(id, updatedProposalDto);
        return ResponseEntity.ok("Proposal updated");
    }

    @DeleteMapping(path = "/deleteProposal{id}")
    public ResponseEntity<String> deleteProposal (@PathVariable int id){
        proposalService.delete(id);
        return ResponseEntity.ok("Proposal deleted");
    }

}

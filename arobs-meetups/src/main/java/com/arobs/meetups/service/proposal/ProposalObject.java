package com.arobs.meetups.service.proposal;

import com.arobs.meetups.entities.Proposal;
import com.arobs.meetups.repositories.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProposalObject {

    @Autowired
    ProposalRepository proposalRepository;

    @Autowired
    ProposalMapper proposalMapper;

    public ProposalDto findById(int idProposal) throws ClassNotFoundException {
        Proposal requestedProposal = proposalRepository.findById(idProposal);
        if(requestedProposal != null) {
            return proposalMapper.map(requestedProposal, ProposalDto.class);
        }
        return null;
    }

    public List<ProposalDto> getAllProposals() {
        List<Proposal> requestedProposals = proposalRepository.getAll();
        List<ProposalDto> requestedProposalsDto = new ArrayList<>();
        if(!requestedProposals.isEmpty()){
            for(Proposal proposal : requestedProposals){
                requestedProposalsDto.add(proposalMapper.map(proposal, ProposalDto.class));
            }
            return requestedProposalsDto;
        }
        return null;

    }

    public List<ProposalDto> getAllWithSameDifficulty(String difficulty) {
        List<Proposal> requestedProposals = proposalRepository.getAllWithSameDifficulty(difficulty);
        List<ProposalDto> requestedProposalsDto = new ArrayList<>();
        if(!requestedProposals.isEmpty()){
            for(Proposal proposal : requestedProposals){
                requestedProposalsDto.add(proposalMapper.map(proposal, ProposalDto.class));
            }
            return requestedProposalsDto;
        }
        return null;
    }

    public List<ProposalDto> getAllWithSameLanguage(String language) {
        List<Proposal> requestedProposals = proposalRepository.getAllWithSameLanguage(language);
        List<ProposalDto> requestedProposalsDto = new ArrayList<>();
        if(!requestedProposals.isEmpty()){
            for(Proposal proposal : requestedProposals){
                requestedProposalsDto.add(proposalMapper.map(proposal, ProposalDto.class));
            }
            return requestedProposalsDto;
        }
        return null;
    }

    public List<ProposalDto> getAllWithSameType(String type) {
        List<Proposal> requestedProposals = proposalRepository.getAllWithSameType(type);
        List<ProposalDto> requestedProposalsDto = new ArrayList<>();
        if(!requestedProposals.isEmpty()){
            for(Proposal proposal : requestedProposals){
                requestedProposalsDto.add(proposalMapper.map(proposal, ProposalDto.class));
            }
            return requestedProposalsDto;
        }
        return null;
    }

    public List<ProposalDto> getAllOfAnUser(int idUser) {
        List<Proposal> requestedProposals = proposalRepository.getAllOfAnUser(idUser);
        List<ProposalDto> requestedProposalsDto = new ArrayList<>();
        if(!requestedProposals.isEmpty()){
            for(Proposal proposal : requestedProposals){
                requestedProposalsDto.add(proposalMapper.map(proposal, ProposalDto.class));
            }
            return requestedProposalsDto;
        }
        return null;
    }

    public void create(ProposalDto newProposalDto) {
        Proposal newProposal = proposalMapper.map(newProposalDto, Proposal.class);
        proposalRepository.create(newProposal);
    }

    public void update(int idProposal, ProposalDto updatedProposalDto) {
        Proposal requestedProposal = proposalRepository.findById(idProposal);
        Proposal updatedProposal = proposalMapper.map(updatedProposalDto, Proposal.class);
        requestedProposal.setDescription(updatedProposal.getDescription());
        requestedProposal.setDifficulty(updatedProposal.getDifficulty());
        requestedProposal.setDurationInMinutes(updatedProposal.getDurationInMinutes());
        requestedProposal.setLanguage(updatedProposal.getLanguage());
        requestedProposal.setMaximumPeople(updatedProposal.getMaximumPeople());
        requestedProposal.setTitle(updatedProposal.getTitle());
        requestedProposal.setType(updatedProposal.getType());
        requestedProposal.setUser(updatedProposal.getUser());
        proposalRepository.update(requestedProposal);
    }

    public void delete(int idProposal) {
        Proposal proposalToDelete = proposalRepository.findById(idProposal);
        if(proposalToDelete != null){
            proposalRepository.delete(proposalToDelete);
        }
    }

}

package com.arobs.meetups.service.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProposalServiceImplementation implements IProposalService{

    @Autowired
    ProposalObject proposalObject;

    @Override
    @Transactional
    public ProposalDto findById(int idProposal) throws ClassNotFoundException {
        return proposalObject.findById(idProposal);
    }

    @Override
    @Transactional
    public List<ProposalDto> getAllProposals() {
        return proposalObject.getAllProposals();
    }

    @Override
    @Transactional
    public List<ProposalDto> getAllWithSameDifficulty(String difficulty) {
        return proposalObject.getAllWithSameDifficulty(difficulty);
    }

    @Override
    @Transactional
    public List<ProposalDto> getAllWithSameLanguage(String language) {
        return proposalObject.getAllWithSameLanguage(language);
    }

    @Override
    @Transactional
    public List<ProposalDto> getAllWithSameType(String type) {
        return proposalObject.getAllWithSameType(type);
    }

    @Override
    @Transactional
    public List<ProposalDto> getAllOfAnUser(int idUser) {
        return proposalObject.getAllOfAnUser(idUser);
    }

    @Override
    @Transactional
    public void createProposal(ProposalDto proposalDto) {
        proposalObject.createProposal(proposalDto);
    }

    @Override
    @Transactional
    public void updateProposal(int idProposal, ProposalDto updatedProposalDto) {
        proposalObject.updateProposal(idProposal, updatedProposalDto);
    }

    @Override
    @Transactional
    public void deleteProposal(int idProposal) {
        proposalObject.deleteProposal(idProposal);
    }
}

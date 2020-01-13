package com.arobs.meetups.service.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProposalServiceImplementation implements ProposalService {

    @Autowired
    ProposalObject proposalObject;

    @Override
    @Transactional
    public ProposalDto findById(int idProposal) throws ClassNotFoundException {
        return proposalObject.findById(idProposal);
    }

    @Override
    @Transactional
    public List<ProposalDto> getAll() {
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
    public void create(ProposalDto proposalDto) {
        proposalObject.create(proposalDto);
    }

    @Override
    @Transactional
    public void update(int idProposal, ProposalDto updatedProposalDto) {
        proposalObject.update(idProposal, updatedProposalDto);
    }

    @Override
    @Transactional
    public void delete(int idProposal) {
        proposalObject.delete(idProposal);
    }
}

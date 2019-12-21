package com.arobs.meetups.service.proposal;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProposalService {

    ProposalDto findById(int idProposal) throws ClassNotFoundException;
    List<ProposalDto> getAllProposals();
    List<ProposalDto> getAllWithSameDifficulty(String difficulty);
    List<ProposalDto> getAllWithSameLanguage(String language);
    List<ProposalDto> getAllWithSameType(String type);
    List<ProposalDto> getAllOfAnUser(int idUser);
    void createProposal(ProposalDto proposalDto);
    void updateProposal(int idProposal, ProposalDto updatedProposal);
    void deleteProposal(int idProposal);

}

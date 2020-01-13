package com.arobs.meetups.service.proposal;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProposalService {

    ProposalDto findById(int idProposal) throws ClassNotFoundException;
    List<ProposalDto> getAll();
    List<ProposalDto> getAllWithSameDifficulty(String difficulty);
    List<ProposalDto> getAllWithSameLanguage(String language);
    List<ProposalDto> getAllWithSameType(String type);
    List<ProposalDto> getAllOfAnUser(int idUser);
    void create(ProposalDto proposalDto);
    void update(int idProposal, ProposalDto updatedProposal);
    void delete(int idProposal);

}

package com.arobs.meetups.service.awardinghistory;

import com.arobs.meetups.entities.AwardingHistory;
import com.arobs.meetups.repositories.Award;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AwardingHistoryServiceImplementation implements IAwardingHistoryService {

    @Autowired
    AwardingHistoryObject awardingHistoryObject;

    @Override
    @Transactional
    public AwardingHistoryDto findById(int idAwardingHistory) {
        return awardingHistoryObject.findById(idAwardingHistory);
    }

    @Override
    @Transactional
    public List<AwardingHistoryDto> getAll() {
        return awardingHistoryObject.getAll();
    }

    @Override
    @Transactional
    public List<AwardingHistoryDto> getAllAwardingsOfAnUser(int idUser) {
        return awardingHistoryObject.getAllAwardingsOfAnUser(idUser);
    }

    @Override
    @Transactional
    public List<Award> getAllAwardingsOfTheYear(String year) {
        return awardingHistoryObject.getAllAwardingsOfTheYear(year);
    }

    @Override
    @Transactional
    public void createAwardingHistory(AwardingHistoryDto newAwardingHistoryDto) {
        awardingHistoryObject.createAwardingHistory(newAwardingHistoryDto);
    }

    @Override
    @Transactional
    public void updateAwardingHistory(int idAwardingHistory, AwardingHistoryDto updatedAwardingHistoryDto) {
        awardingHistoryObject.updateAwardingHistory(idAwardingHistory, updatedAwardingHistoryDto);
    }

    @Override
    @Transactional
    public void deleteAwardingHistory(int idAwardingHistoryToDelete) {
        awardingHistoryObject.deleteAwardingHistory(idAwardingHistoryToDelete);
    }
}

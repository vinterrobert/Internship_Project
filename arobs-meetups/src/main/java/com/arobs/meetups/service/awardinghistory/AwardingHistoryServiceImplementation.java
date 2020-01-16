package com.arobs.meetups.service.awardinghistory;

import com.arobs.meetups.repositories.Award;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AwardingHistoryServiceImplementation implements AwardingHistoryService {

    @Autowired
    AwardingHistoryObject awardingHistoryObject;

    @Override
    @Transactional
    public AwardingHistoryDto findById(int idAwardingHistory) throws Exception{
        return awardingHistoryObject.findById(idAwardingHistory);
    }

    @Override
    @Transactional
    public List<AwardingHistoryDto> getAll() {
        return awardingHistoryObject.getAll();
    }

    @Override
    @Transactional
    public List<AwardingHistoryDto> getAllOfAnUser(int idUser) {
        return awardingHistoryObject.getAllOfAnUser(idUser);
    }

    @Override
    @Transactional
    public List<Award> getAllOfTheYear(String year) {
        return awardingHistoryObject.getAllOfTheYear(year);
    }

    @Override
    @Transactional
    public void create(AwardingHistoryDto newAwardingHistoryDto) {
        awardingHistoryObject.create(newAwardingHistoryDto);
    }

    @Override
    @Transactional
    public void update(int idAwardingHistory, AwardingHistoryDto updatedAwardingHistoryDto) throws Exception{
        awardingHistoryObject.update(idAwardingHistory, updatedAwardingHistoryDto);
    }

    @Override
    @Transactional
    public void delete(int idAwardingHistoryToDelete) throws Exception{
        awardingHistoryObject.delete(idAwardingHistoryToDelete);
    }
}

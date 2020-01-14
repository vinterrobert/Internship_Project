package com.arobs.meetups.service.awardinghistory;

import com.arobs.meetups.entities.AwardingHistory;
import com.arobs.meetups.repositories.Award;
import com.arobs.meetups.repositories.AwardingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AwardingHistoryObject {

    @Autowired
    AwardingHistoryRepository awardingHistoryRepository;

    @Autowired
    AwardingHistoryMapper awardingHistoryMapper;

    public AwardingHistoryDto findById(int idAwardingHistory){
        AwardingHistory requestedAwardingHistory = awardingHistoryRepository.findById(idAwardingHistory);
        return awardingHistoryMapper.map(requestedAwardingHistory, AwardingHistoryDto.class);
    }

    public List<AwardingHistoryDto> getAll(){
       List<AwardingHistory> awardingHistoryList = awardingHistoryRepository.getAll();
       List<AwardingHistoryDto> awardingHistoryDtos = new ArrayList<>();
       if(!awardingHistoryList.isEmpty()){
           for(AwardingHistory awardingHistory : awardingHistoryList){
                awardingHistoryDtos.add(awardingHistoryMapper.map(awardingHistory, AwardingHistoryDto.class));
           }
       }
        return awardingHistoryDtos;
    }

    public List<AwardingHistoryDto> getAllOfAnUser(int idUser){
        List<AwardingHistory> awardingHistoryList = awardingHistoryRepository.getAllOfAnUser(idUser);
        List<AwardingHistoryDto> awardingHistoryDtos = new ArrayList<>();
        if(!awardingHistoryList.isEmpty()){
            for(AwardingHistory awardingHistory : awardingHistoryList){
                awardingHistoryDtos.add(awardingHistoryMapper.map(awardingHistory, AwardingHistoryDto.class));
            }
        }
        return awardingHistoryDtos;
    }

    public List<Award> getAllOfTheYear(String year){
        return awardingHistoryRepository.getAllOfTheYear(year);
    }

    public void create(AwardingHistoryDto newAwardingHistoryDto){
        AwardingHistory newAwardingHistory = awardingHistoryMapper.map(newAwardingHistoryDto,AwardingHistory.class);
        awardingHistoryRepository.create(newAwardingHistory);
    }

    public void update(int idAwardingHistory, AwardingHistoryDto updatedAwardingHistoryDto){
        AwardingHistory currentAwardingHistory = awardingHistoryRepository.findById(idAwardingHistory);
        currentAwardingHistory.setAwardingDate(updatedAwardingHistoryDto.getAwardingDate());
        currentAwardingHistory.setPoints(updatedAwardingHistoryDto.getPoints());
        currentAwardingHistory.setPrize(updatedAwardingHistoryDto.getPrize());
        currentAwardingHistory.setUser(updatedAwardingHistoryDto.getUser());
        awardingHistoryRepository.update(currentAwardingHistory);
    }

    public void delete(int idAwardingHistory){
        AwardingHistory awardingHistoryToDelete = awardingHistoryRepository.findById(idAwardingHistory);
        awardingHistoryRepository.delete(awardingHistoryToDelete);
    }
}

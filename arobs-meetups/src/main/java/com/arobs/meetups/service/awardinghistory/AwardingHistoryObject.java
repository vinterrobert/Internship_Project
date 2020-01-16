package com.arobs.meetups.service.awardinghistory;

import com.arobs.meetups.entities.AwardingHistory;
import com.arobs.meetups.repositories.Award;
import com.arobs.meetups.repositories.AwardingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class AwardingHistoryObject {

    @Autowired
    AwardingHistoryRepository awardingHistoryRepository;

    @Autowired
    AwardingHistoryMapper awardingHistoryMapper;

    public AwardingHistoryDto findById(int idAwardingHistory) throws Exception{
        AwardingHistory requestedAwardingHistory = awardingHistoryRepository.findById(idAwardingHistory);
        if(requestedAwardingHistory != null){
            return awardingHistoryMapper.map(requestedAwardingHistory, AwardingHistoryDto.class);
        }
        else{
            throw new Exception ("Awarding History doesn't exist!");
        }
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

    public boolean isDateOk(Timestamp selectedDate){
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        if(selectedDate.before(currentDate)){
            return false;
        }
        return true;
    }

    public void update(int idAwardingHistory, AwardingHistoryDto updatedAwardingHistoryDto) throws Exception{
        AwardingHistory currentAwardingHistory = awardingHistoryRepository.findById(idAwardingHistory);
        currentAwardingHistory.setAwardingDate(updatedAwardingHistoryDto.getAwardingDate());
        currentAwardingHistory.setPoints(updatedAwardingHistoryDto.getPoints());
        currentAwardingHistory.setPrize(updatedAwardingHistoryDto.getPrize());
        currentAwardingHistory.setUser(updatedAwardingHistoryDto.getUser());
        awardingHistoryRepository.update(currentAwardingHistory);
    }

    public void delete(int idAwardingHistory) throws Exception {
        AwardingHistory awardingHistoryToDelete = awardingHistoryRepository.findById(idAwardingHistory);
        awardingHistoryRepository.delete(awardingHistoryToDelete);
    }
}

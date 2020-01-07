package com.arobs.meetups.service.awardinghistory;

import com.arobs.meetups.entities.AwardingHistory;
import com.arobs.meetups.entities.User;
import com.arobs.meetups.repositories.Award;
import com.arobs.meetups.repositories.AwardingHistoryRepository;
import com.arobs.meetups.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AwardingHistoryObject {

    @Autowired
    AwardingHistoryRepository awardingHistoryRepository;

    @Autowired
    UserRepository userRepository;

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

    public List<AwardingHistoryDto> getAllAwardingsOfAnUser(int idUser){
        List<AwardingHistory> awardingHistoryList = awardingHistoryRepository.getAllAwardingsOfAnUser(idUser);
        List<AwardingHistoryDto> awardingHistoryDtos = new ArrayList<>();
        if(!awardingHistoryList.isEmpty()){
            for(AwardingHistory awardingHistory : awardingHistoryList){
                awardingHistoryDtos.add(awardingHistoryMapper.map(awardingHistory, AwardingHistoryDto.class));
            }
        }
        return awardingHistoryDtos;
    }

    public List<Award> getAllAwardingsOfTheYear(String year){
        return awardingHistoryRepository.getAllAwardingsOfTheYear(year);
    }

    public void createAwardingHistory(AwardingHistoryDto newAwardingHistoryDto){
        AwardingHistory newAwardingHistory = awardingHistoryMapper.map(newAwardingHistoryDto,AwardingHistory.class);
        awardingHistoryRepository.createAwardingHistory(newAwardingHistory);
        //Updating users points
        int idAwardedUser = newAwardingHistoryDto.getUser().getId();
        User awardedUser = userRepository.findById(idAwardedUser);
        int currentPoints = awardedUser.getPoints();
        awardedUser.setPoints(currentPoints + newAwardingHistoryDto.getPoints());
        userRepository.update(awardedUser);
    }

    public void updateAwardingHistory(int idAwardingHistory, AwardingHistoryDto updatedAwardingHistoryDto){
        AwardingHistory currentAwardingHistory = awardingHistoryRepository.findById(idAwardingHistory);
        int currentAwardingHistoryPoints = currentAwardingHistory.getPoints();
        currentAwardingHistory.setAwardingDate(updatedAwardingHistoryDto.getAwardingDate());
        currentAwardingHistory.setPoints(updatedAwardingHistoryDto.getPoints());
        currentAwardingHistory.setPrize(updatedAwardingHistoryDto.getPrize());
        currentAwardingHistory.setUser(updatedAwardingHistoryDto.getUser());
        awardingHistoryRepository.updateAwardingHistory(currentAwardingHistory);
        //Updating users points
        int idAwardedUser = updatedAwardingHistoryDto.getUser().getId();
        User awardedUser = userRepository.findById(idAwardedUser);
        int currentPoints = awardedUser.getPoints();
        awardedUser.setPoints(currentPoints - currentAwardingHistoryPoints + updatedAwardingHistoryDto.getPoints());
        userRepository.update(awardedUser);
    }

    public void deleteAwardingHistory(int idAwardingHistory){
        AwardingHistory awardingHistoryToDelete = awardingHistoryRepository.findById(idAwardingHistory);
        awardingHistoryRepository.deleteAwardingHistory(awardingHistoryToDelete);
        //Updating users points
        int idAwardedUser = awardingHistoryToDelete.getUser().getId();
        User awardedUser = userRepository.findById(idAwardedUser);
        int currentPoints = awardedUser.getPoints();
        awardedUser.setPoints(currentPoints - awardingHistoryToDelete.getPoints());
        userRepository.update(awardedUser);
    }
}

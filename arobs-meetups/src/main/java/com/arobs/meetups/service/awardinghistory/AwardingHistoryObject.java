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
        updateUserPoints(-1, newAwardingHistory.getUser().getPoints(), newAwardingHistoryDto, "create");
    }

    public void update(int idAwardingHistory, AwardingHistoryDto updatedAwardingHistoryDto){
        AwardingHistory currentAwardingHistory = awardingHistoryRepository.findById(idAwardingHistory);
        int currentAwardingHistoryPoints = currentAwardingHistory.getPoints();
        currentAwardingHistory.setAwardingDate(updatedAwardingHistoryDto.getAwardingDate());
        currentAwardingHistory.setPoints(updatedAwardingHistoryDto.getPoints());
        currentAwardingHistory.setPrize(updatedAwardingHistoryDto.getPrize());
        updateUserPoints(idAwardingHistory, currentAwardingHistoryPoints, updatedAwardingHistoryDto, "update");
        currentAwardingHistory.setUser(updatedAwardingHistoryDto.getUser());
        awardingHistoryRepository.update(currentAwardingHistory);
    }

    public void delete(int idAwardingHistory){
        AwardingHistory awardingHistoryToDelete = awardingHistoryRepository.findById(idAwardingHistory);
        updateUserPoints(idAwardingHistory, awardingHistoryToDelete.getUser().getPoints(), null, "delete");
        awardingHistoryRepository.delete(awardingHistoryToDelete);
    }

    void updateUserPoints(int idAwardingHistory, int currentAwardingHistoryPoints, AwardingHistoryDto awardingHistoryDto, String sqlOperation){
        AwardingHistory awardingHistory;
        int idAwardedUser = -1;
        int newIdAwardedUser = -1;
        User awardedUser;
        User newAwardedUser;
        int currentPointsAwardedUser = 0;
        int currentPointsNewAwardedUser = 0;

        if(idAwardingHistory == -1){
            awardingHistory = null;
        }
        else{
            awardingHistory = awardingHistoryRepository.findById(idAwardingHistory);
        }

        if(awardingHistory != null && awardingHistoryDto != null) {
            if (awardingHistory.getUser().getId() == awardingHistoryDto.getUser().getId()) {
                idAwardedUser = awardingHistoryDto.getUser().getId();
                newIdAwardedUser = awardingHistoryDto.getUser().getId();
            } else {
                idAwardedUser = awardingHistory.getUser().getId();
                newIdAwardedUser = awardingHistoryDto.getUser().getId();
            }
            awardedUser = userRepository.findById(idAwardedUser);
            newAwardedUser = userRepository.findById(newIdAwardedUser);
            currentPointsAwardedUser = awardedUser.getPoints();
            currentPointsNewAwardedUser = newAwardedUser.getPoints();

            if (sqlOperation.equals("update")) {
                if (awardedUser.getId() == newAwardedUser.getId()) {
                    awardedUser.setPoints(currentPointsAwardedUser - currentAwardingHistoryPoints + awardingHistoryDto.getPoints());
                    userRepository.update(awardedUser);
                } else {
                    newAwardedUser.setPoints(currentPointsNewAwardedUser + awardingHistoryDto.getPoints());
                    userRepository.update(newAwardedUser);
                    awardedUser.setPoints(currentPointsAwardedUser - currentAwardingHistoryPoints);
                    userRepository.update(awardedUser);
                }
            }
        }else{
            if(awardingHistory != null){
                if(sqlOperation.equals("delete")){
                    awardedUser = awardingHistory.getUser();
                    currentPointsAwardedUser = awardedUser.getPoints();
                    awardedUser.setPoints(currentPointsAwardedUser - awardingHistory.getPoints());
                    userRepository.update(awardedUser);
                }
            }else{
                if(awardingHistoryDto != null){
                    if(sqlOperation.equals("create")){
                        newIdAwardedUser = awardingHistoryDto.getUser().getId();
                        newAwardedUser = userRepository.findById(newIdAwardedUser);
                        currentPointsNewAwardedUser = newAwardedUser.getPoints();
                        newAwardedUser.setPoints(currentPointsNewAwardedUser + awardingHistoryDto.getPoints());
                        userRepository.update(newAwardedUser);
                    }
                }
            }
        }
    }
}

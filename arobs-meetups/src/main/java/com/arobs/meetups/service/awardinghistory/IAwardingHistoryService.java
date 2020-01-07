package com.arobs.meetups.service.awardinghistory;

import com.arobs.meetups.entities.AwardingHistory;
import com.arobs.meetups.repositories.Award;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface IAwardingHistoryService {
    AwardingHistoryDto findById(int idAwardingHistory);
    List<AwardingHistoryDto> getAll();
    List<AwardingHistoryDto> getAllAwardingsOfAnUser(int idUser);
    List<Award> getAllAwardingsOfTheYear(String year);
    void createAwardingHistory(AwardingHistoryDto newAwardingHistory);
    void updateAwardingHistory(int idAwardingHistory, AwardingHistoryDto updatedAwardingHistoryDto);
    void deleteAwardingHistory(int idAwardingHistoryToDelete);
}

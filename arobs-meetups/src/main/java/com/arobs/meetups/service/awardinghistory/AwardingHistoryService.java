package com.arobs.meetups.service.awardinghistory;

import com.arobs.meetups.entities.AwardingHistory;
import com.arobs.meetups.repositories.Award;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface AwardingHistoryService {
    AwardingHistoryDto findById(int idAwardingHistory) throws Exception;
    List<AwardingHistoryDto> getAll();
    List<AwardingHistoryDto> getAllOfAnUser(int idUser);
    List<Award> getAllOfTheYear(String year);
    void create(AwardingHistoryDto newAwardingHistory);
    void update(int idAwardingHistory, AwardingHistoryDto updatedAwardingHistoryDto) throws Exception;
    void delete(int idAwardingHistoryToDelete) throws Exception;
}

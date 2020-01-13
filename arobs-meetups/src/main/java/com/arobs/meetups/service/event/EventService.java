package com.arobs.meetups.service.event;

import com.arobs.meetups.service.event.EventDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface EventService {

    EventDto findById(int idEvent) throws ClassNotFoundException;
    List<EventDto> getAll();
    List<EventDto> getAllWithSameDifficulty(String difficulty);
    List<EventDto> getAllWithSameLanguage(String language);
    List<EventDto> getAllOfAnUser(int idUser);
    List<EventDto> getAllWithSameRoom(String room);
    List<EventDto> getAllFromAPeriod(Timestamp startDate, Timestamp endDate);
    void create(int idProposal, String room, Timestamp date);
    void update(int idEvent, EventDto updatedEvent);
    void delete(int idEvent);

}

package com.arobs.meetups.service.event;

import com.arobs.meetups.service.event.EventDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface IEventService {

    EventDto findById(int idEvent) throws ClassNotFoundException;
    List<EventDto> getAllEvents();
    List<EventDto> getAllWithSameDifficulty(String difficulty);
    List<EventDto> getAllWithSameLanguage(String language);
    List<EventDto> getAllOfAnUser(int idUser);
    List<EventDto> getAllWithSameRoom(String room);
    List<EventDto> getAllEventsFromAPeriod(Timestamp startDate, Timestamp endDate);
    void createEvent(int idProposal, String room, Timestamp date);
    void updateEvent(int idEvent, EventDto updatedEvent);
    void deleteEvent(int idEvent);

}

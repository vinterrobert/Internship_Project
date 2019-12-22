package com.arobs.meetups.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Component
public class EventServiceImplementation implements IEventService {

    @Autowired
    EventObject eventObject;

    @Override
    @Transactional
    public EventDto findById(int idEvent) throws ClassNotFoundException {
        return eventObject.findById(idEvent);
    }

    @Override
    @Transactional
    public List<EventDto> getAllEvents() {
        return eventObject.getAllEvents();
    }

    @Override
    @Transactional
    public List<EventDto> getAllWithSameDifficulty(String difficulty) {
        return eventObject.getAllWithSameDifficulty(difficulty);
    }

    @Override
    @Transactional
    public List<EventDto> getAllWithSameLanguage(String language) {
        return eventObject.getAllWithSameLanguage(language);
    }

    @Override
    @Transactional
    public List<EventDto> getAllOfAnUser(int idUser) {
        return eventObject.getAllOfAnUser(idUser);
    }

    @Override
    @Transactional
    public List<EventDto> getAllWithSameRoom(String room) {
        return eventObject.getAllWithSameRoom(room);
    }

    @Override
    @Transactional
    public List<EventDto> getAllEventsFromAPeriod(Timestamp startDate, Timestamp endDate) {
        return eventObject.getAllEventsFromAPeriod(startDate, endDate);
    }

    @Override
    @Transactional
    public void createEvent(int idProposal, String room, Timestamp date) {
        eventObject.createEvent(idProposal, room, date);
    }

    @Override
    @Transactional
    public void updateEvent(int idEvent, EventDto updatedEvent) {
        eventObject.updateEvent(idEvent, updatedEvent);
    }

    @Override
    @Transactional
    public void deleteEvent(int idEvent) {
        eventObject.deleteEvent(idEvent);
    }
}

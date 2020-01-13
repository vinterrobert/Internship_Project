package com.arobs.meetups.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Component
public class EventServiceImplementation implements EventService {

    @Autowired
    EventObject eventObject;

    @Override
    @Transactional
    public EventDto findById(int idEvent) throws ClassNotFoundException {
        return eventObject.findById(idEvent);
    }

    @Override
    @Transactional
    public List<EventDto> getAll() {
        return eventObject.getAll();
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
    public List<EventDto> getAllFromAPeriod(Timestamp startDate, Timestamp endDate) {
        return eventObject.getAllFromAPeriod(startDate, endDate);
    }

    @Override
    @Transactional
    public void create(int idProposal, String room, Timestamp date) {
        eventObject.create(idProposal, room, date);
    }

    @Override
    @Transactional
    public void update(int idEvent, EventDto updatedEvent) {
        eventObject.update(idEvent, updatedEvent);
    }

    @Override
    @Transactional
    public void delete(int idEvent) {
        eventObject.delete(idEvent);
    }
}

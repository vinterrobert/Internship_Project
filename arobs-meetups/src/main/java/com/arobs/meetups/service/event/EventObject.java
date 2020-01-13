package com.arobs.meetups.service.event;

import com.arobs.meetups.entities.Event;
import com.arobs.meetups.entities.Proposal;
import com.arobs.meetups.repositories.EventRepository;
import com.arobs.meetups.repositories.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventObject {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ProposalRepository proposalRepository;

    @Autowired
    EventMapper eventMapper;

    public EventDto findById(int idEvent){
        Event requestedEvent = eventRepository.findById(idEvent);
        if(requestedEvent != null) {
            return eventMapper.map(requestedEvent, EventDto.class);
        }
        return null;
    }

    public List<EventDto> getAll() {
        List<Event> requestedEvents = eventRepository.getAll();
        List<EventDto> requestedEventsDto = new ArrayList<>();
        if(!requestedEvents.isEmpty()){
            for(Event event : requestedEvents){
                requestedEventsDto.add(eventMapper.map(event, EventDto.class));
            }
            return requestedEventsDto;
        }
        return null;

    }

    public List<EventDto> getAllWithSameDifficulty(String difficulty) {
        List<Event> requestedEvents = eventRepository.getAllWithSameDifficulty(difficulty);
        List<EventDto> requestedEventsDto = new ArrayList<>();
        if(!requestedEventsDto.isEmpty()){
            for(Event event : requestedEvents){
                requestedEventsDto.add(eventMapper.map(event, EventDto.class));
            }
            return requestedEventsDto;
        }
        return null;
    }

    public List<EventDto> getAllWithSameLanguage(String language) {
        List<Event> requestedEvents = eventRepository.getAllWithSameLanguage(language);
        List<EventDto> requestedEventsDto = new ArrayList<>();
        if(!requestedEvents.isEmpty()){
            for(Event event : requestedEvents){
                requestedEventsDto.add(eventMapper.map(event, EventDto.class));
            }
            return requestedEventsDto;
        }
        return null;
    }

    public List<EventDto> getAllOfAnUser(int idUser) {
        List<Event> requestedEvents = eventRepository.getAllOfAnUser(idUser);
        List<EventDto> requestedEventsDto = new ArrayList<>();
        if(!requestedEvents.isEmpty()){
            for(Event proposal : requestedEvents){
                requestedEventsDto.add(eventMapper.map(proposal, EventDto.class));
            }
            return requestedEventsDto;
        }
        return null;
    }

    public List<EventDto> getAllWithSameRoom(String room) {
        List<Event> requestedEvents = eventRepository.getAllWithSameRoom(room);
        List<EventDto> requestedEventsDto = new ArrayList<>();
        if(!requestedEvents.isEmpty()){
            for(Event proposal : requestedEvents){
                requestedEventsDto.add(eventMapper.map(proposal, EventDto.class));
            }
            return requestedEventsDto;
        }
        return null;
    }

    public List<EventDto> getAllFromAPeriod(Timestamp startDate, Timestamp endDate) {
        List<Event> requestedEvents = eventRepository.getAllFromAPeriod(startDate, endDate);
        List<EventDto> requestedEventsDto = new ArrayList<>();
        if(!requestedEvents.isEmpty()){
            for(Event proposal : requestedEvents){
                requestedEventsDto.add(eventMapper.map(proposal, EventDto.class));
            }
            return requestedEventsDto;
        }
        return null;
    }

    public void create(int idProposal, String room, Timestamp date) {
        Proposal requestedProposal = proposalRepository.findById(idProposal);
        Event newEvent = new Event();
        newEvent.setDescription(requestedProposal.getDescription());
        newEvent.setDifficulty(requestedProposal.getDifficulty());
        newEvent.setDurationInMinutes(requestedProposal.getDurationInMinutes());
        newEvent.setLanguage(requestedProposal.getLanguage());
        newEvent.setTitle(requestedProposal.getTitle());
        newEvent.setMaximumPeople(requestedProposal.getMaximumPeople());
        newEvent.setUser(requestedProposal.getUser());
        newEvent.setRoom(room);
        newEvent.setDate(date);
        eventRepository.create(newEvent);
        //Deleting proposal
        proposalRepository.delete(requestedProposal);
    }

    public void update(int idEvent, EventDto updatedEventDto) {
        Event requestedEvent = eventRepository.findById(idEvent);
        Event updatedEvent = eventMapper.map(updatedEventDto, Event.class);
        requestedEvent.setDescription(updatedEvent.getDescription());
        requestedEvent.setDifficulty(updatedEvent.getDifficulty());
        requestedEvent.setDurationInMinutes(updatedEvent.getDurationInMinutes());
        requestedEvent.setLanguage(updatedEvent.getLanguage());
        requestedEvent.setMaximumPeople(updatedEvent.getMaximumPeople());
        requestedEvent.setTitle(updatedEvent.getTitle());
        requestedEvent.setUser(updatedEvent.getUser());
        requestedEvent.setRoom(updatedEvent.getRoom());
        requestedEvent.setDate(updatedEvent.getDate());
        eventRepository.update(requestedEvent);
    }

    public void delete(int idEvent) {
        Event eventToDelete = eventRepository.findById(idEvent);
        if(eventToDelete != null){
            eventRepository.delete(eventToDelete);
        }
    }

}

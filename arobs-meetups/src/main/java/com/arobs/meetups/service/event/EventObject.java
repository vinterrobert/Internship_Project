package com.arobs.meetups.service.event;

import com.arobs.meetups.entities.Attendance;
import com.arobs.meetups.entities.Event;
import com.arobs.meetups.entities.Proposal;
import com.arobs.meetups.entities.User;
import com.arobs.meetups.repositories.EventRepository;
import com.arobs.meetups.repositories.ProposalRepository;
import com.arobs.meetups.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@EnableAsync
@EnableScheduling
public class EventObject {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ProposalRepository proposalRepository;

    @Autowired
    UserRepository userRepository;

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
        deleteVotesOfProposal(requestedProposal);
        //Deleting proposal
        proposalRepository.delete(requestedProposal);
    }

    void deleteVotesOfProposal(Proposal acceptedProposal){
        Set<User> votersOfTheProposal = acceptedProposal.getUsers();
        for(User voter : votersOfTheProposal){
            voter.getVotedProposals().remove(acceptedProposal);
        }
        acceptedProposal.getUsers().clear();
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

    @Async
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void updateUsersPointsFromEvent(){
        List<Event> eventsList = eventRepository.getAll();
        for(Event event: eventsList){
            Timestamp currentDate = new Timestamp(System.currentTimeMillis());
            Timestamp eventDate = event.getDate();

            if(currentDate.after(eventDate) && !(event.isClosed())){
                event.setClosed(true);
                eventRepository.update(event);

                Set<Attendance> attendancesSet = event.getAttendees();
                for(Attendance attendance : attendancesSet){
                    User attendee = attendance.getUser();
                    attendee.setPoints(attendee.getPoints() + 5);
                    userRepository.update(attendee);
                }
                User presenter = event.getUser();
                int currentPointsOfPresenter = presenter.getPoints();
                int updatedPointsOfPresenter = currentPointsOfPresenter;

                switch(event.getDifficulty()){
                    case "easy":
                        updatedPointsOfPresenter += 20;
                        break;
                    case "medium":
                        updatedPointsOfPresenter += 35;
                        break;
                    case "difficult":
                        updatedPointsOfPresenter += 50;
                        break;
                }
                presenter.setPoints(updatedPointsOfPresenter);
                userRepository.update(presenter);
            }
        }
    }
}

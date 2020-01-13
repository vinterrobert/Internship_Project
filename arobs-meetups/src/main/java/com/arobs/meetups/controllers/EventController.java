package com.arobs.meetups.controllers;

import com.arobs.meetups.service.event.EventDto;
import com.arobs.meetups.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    EventService eventService;

    @GetMapping(path = "/id{idEvent}")
    public ResponseEntity<EventDto> findEventById(@PathVariable int idEvent) throws ClassNotFoundException {
        return ResponseEntity.ok(eventService.findById(idEvent));
    }

    @GetMapping (path = "/allEvents")
    public ResponseEntity<List<EventDto>> getAllEvents () throws ClassNotFoundException{
        return ResponseEntity.ok(eventService.getAll());
    }

    @GetMapping (path = "/difficulty{difficulty}")
    public ResponseEntity<List<EventDto>> getAllEventsWithSameDifficulty (@PathVariable String difficulty) throws ClassNotFoundException{
        return ResponseEntity.ok(eventService.getAllWithSameDifficulty(difficulty));
    }

    @GetMapping (path = "/language{language}")
    public ResponseEntity<List<EventDto>> getAllEventsWithSameLanguage (@PathVariable String language) throws ClassNotFoundException{
        return ResponseEntity.ok(eventService.getAllWithSameLanguage(language));
    }

    @GetMapping (path = "/room{room}")
    public ResponseEntity<List<EventDto>> getAllEventsWithSameRoom (@PathVariable String room) throws ClassNotFoundException{
        return ResponseEntity.ok(eventService.getAllWithSameRoom(room));
    }

    @GetMapping (path = "/period/start{startDate}/end{endDate}")
    public ResponseEntity<List<EventDto>> getAllEventsInAPeriod (@PathVariable Timestamp startDate, @PathVariable Timestamp endDate) throws ClassNotFoundException{
        return ResponseEntity.ok(eventService.getAllFromAPeriod(startDate, endDate));
    }

    @GetMapping (path = "/user{id}")
    public ResponseEntity<List<EventDto>> getAllEventsOfAnUser (@PathVariable int id) throws ClassNotFoundException{
        return ResponseEntity.ok(eventService.getAllOfAnUser(id));
    }

    @PostMapping(path = "/createEvent{idProposal}/{room}/{date}")
    public ResponseEntity<String> createEvent(@PathVariable int idProposal, @PathVariable String room, @PathVariable Timestamp date) {
        eventService.create(idProposal, room, date);
        return ResponseEntity.ok("Event created");
    }

    @PutMapping(path = "/updateEvent{id}")
    public ResponseEntity<String> updateEvent (@RequestBody EventDto updatedEventDto, @PathVariable int id){
        eventService.update(id, updatedEventDto);
        return ResponseEntity.ok("Event updated");
    }

    @DeleteMapping(path = "/deleteEvent{id}")
    public ResponseEntity<String> deleteEvent (@PathVariable int id){
        eventService.delete(id);
        return ResponseEntity.ok("Event deleted");
    }


}

package com.arobs.meetups.controllers;

import com.arobs.meetups.service.event.EventDto;
import com.arobs.meetups.service.event.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    IEventService eventService;

    @GetMapping(path = "/id{idEvent}")
    public ResponseEntity<EventDto> findById(@PathVariable int idEvent) throws ClassNotFoundException {
        return ResponseEntity.ok(eventService.findById(idEvent));
    }

    @GetMapping (path = "/allEvents")
    public ResponseEntity<List<EventDto>> getAllEvents () throws ClassNotFoundException{
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping (path = "/difficulty{difficulty}")
    public ResponseEntity<List<EventDto>> getAllWithSameDifficulty (@PathVariable String difficulty) throws ClassNotFoundException{
        return ResponseEntity.ok(eventService.getAllWithSameDifficulty(difficulty));
    }

    @GetMapping (path = "/language{language}")
    public ResponseEntity<List<EventDto>> getAllWithSameLanguage (@PathVariable String language) throws ClassNotFoundException{
        return ResponseEntity.ok(eventService.getAllWithSameLanguage(language));
    }

    @GetMapping (path = "/room{room}")
    public ResponseEntity<List<EventDto>> getAllWithSameRoom (@PathVariable String room) throws ClassNotFoundException{
        return ResponseEntity.ok(eventService.getAllWithSameRoom(room));
    }

    @GetMapping (path = "/period{startDate}-{endDate}")
    public ResponseEntity<List<EventDto>> getAllWithSameRoom (@PathVariable Timestamp startDate, @PathVariable Timestamp endDate) throws ClassNotFoundException{
        return ResponseEntity.ok(eventService.getAllEventsFromAPeriod(startDate, endDate));
    }

    @GetMapping (path = "/user{id}")
    public ResponseEntity<List<EventDto>> getAllOfAnUser (@PathVariable int id) throws ClassNotFoundException{
        return ResponseEntity.ok(eventService.getAllOfAnUser(id));
    }

    @PostMapping(path = "/createEvent{idProposal}/{room}/{date}")
    public ResponseEntity<String> createEvent(@PathVariable int idProposal, @PathVariable String room, @PathVariable Timestamp date) {
        eventService.createEvent(idProposal, room, date);
        return ResponseEntity.ok("Event created");
    }

    @PutMapping(path = "/updateEvent{id}")
    public ResponseEntity<String> updateProposal (@RequestBody EventDto updatedEventDto, @PathVariable int id){
        eventService.updateEvent(id, updatedEventDto);
        return ResponseEntity.ok("Event updated");
    }

    @DeleteMapping(path = "/deleteEvent{id}")
    public ResponseEntity<String> deleteEvent (@PathVariable int id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted");
    }


}

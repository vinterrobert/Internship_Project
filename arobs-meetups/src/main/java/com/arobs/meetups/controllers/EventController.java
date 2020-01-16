package com.arobs.meetups.controllers;

import com.arobs.meetups.service.event.EventDto;
import com.arobs.meetups.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity createEvent(@PathVariable int idProposal, @PathVariable String room, @PathVariable Timestamp date) {
        try {
            eventService.create(idProposal, room, date);
            return ResponseEntity.status(HttpStatus.CREATED).body("Event created");
        } catch(Exception e){
            if(e.getMessage().equals("Date is not ok!")){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        }
    }

    @PutMapping(path = "/updateEvent{id}")
    public ResponseEntity updateEvent (@RequestBody EventDto updatedEventDto, @PathVariable int id){
        try{
            eventService.update(id, updatedEventDto);
            return ResponseEntity.status(HttpStatus.OK).body("Event Updated!");
        }catch(Exception e){
            if(e.getMessage().equals("Date is not ok!")){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        }

    }

    @DeleteMapping(path = "/deleteEvent{id}")
    public ResponseEntity<String> deleteEvent (@PathVariable int id){
        eventService.delete(id);
        return ResponseEntity.ok("Event deleted");
    }


}

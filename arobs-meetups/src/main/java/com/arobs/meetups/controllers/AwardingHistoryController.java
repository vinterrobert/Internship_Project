package com.arobs.meetups.controllers;

import com.arobs.meetups.repositories.Award;
import com.arobs.meetups.service.awardinghistory.AwardingHistoryDto;
import com.arobs.meetups.service.awardinghistory.AwardingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/awardingHistory")
public class AwardingHistoryController {

    @Autowired
    AwardingHistoryService awardingHistoryService;

    @GetMapping(path = "/id{idAwardingHistory}")
    public ResponseEntity findAwardingById(@PathVariable int idAwardingHistory){
        try {
            return ResponseEntity.ok(awardingHistoryService.findById(idAwardingHistory));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Awarding doesn't  exist!");
        }
    }

    @GetMapping(path = "/allAwardingsHistory")
    public ResponseEntity<List<AwardingHistoryDto>> getAllAwardings() throws ClassNotFoundException {
        return ResponseEntity.ok(awardingHistoryService.getAll());
    }

    @GetMapping(path = "/allAwardingsHistory/id{idUser}")
    public ResponseEntity<List<AwardingHistoryDto>> getAllAwardingsOfAnUser(@PathVariable int idUser) throws ClassNotFoundException {
        return ResponseEntity.ok(awardingHistoryService.getAllOfAnUser(idUser));
    }

    @GetMapping(path = "/allAwardingsHistory/year{year}")
    public ResponseEntity<List<Award>> getAllAwardingsOfTheYear(@PathVariable String year) throws ClassNotFoundException {
        return ResponseEntity.ok(awardingHistoryService.getAllOfTheYear(year));
    }

    @PostMapping(path = "/createAwardingHistory")
    public ResponseEntity<String> createAwardingHistory(@RequestBody AwardingHistoryDto awardingHistoryDto) throws ClassNotFoundException {
        awardingHistoryService.create(awardingHistoryDto);
        return ResponseEntity.ok("Awarding History created");
    }

    @PutMapping(path = "/updateAwardingHistory/id{idAwardingHistory}")
    public ResponseEntity updateAwardingHistory(@RequestBody AwardingHistoryDto awardingHistoryDto, @PathVariable int idAwardingHistory) throws ClassNotFoundException {
        try {
            awardingHistoryService.update(idAwardingHistory, awardingHistoryDto);
            return ResponseEntity.status(HttpStatus.OK).body("Awarding History updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/deleteAwardingHistory/id{idAwardingHistory}")
    public ResponseEntity<String> deleteAwardingHistory(@PathVariable int idAwardingHistory){
        try{
            awardingHistoryService.delete(idAwardingHistory);
            return ResponseEntity.status(HttpStatus.OK).body("Awarding History deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

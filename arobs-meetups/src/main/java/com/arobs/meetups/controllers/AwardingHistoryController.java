package com.arobs.meetups.controllers;

import com.arobs.meetups.repositories.Award;
import com.arobs.meetups.service.awardinghistory.AwardingHistoryDto;
import com.arobs.meetups.service.awardinghistory.IAwardingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/awardingHistory")
public class AwardingHistoryController {

    @Autowired
    IAwardingHistoryService awardingHistoryService;

    @GetMapping(path = "/id{idAwardingHistory}")
    public ResponseEntity<AwardingHistoryDto> findById(@PathVariable int idAwardingHistory) throws ClassNotFoundException {
        return ResponseEntity.ok(awardingHistoryService.findById(idAwardingHistory));
    }

    @GetMapping(path = "/allAwardingsHistory")
    public ResponseEntity<List<AwardingHistoryDto>> getAll() throws ClassNotFoundException {
        return ResponseEntity.ok(awardingHistoryService.getAll());
    }

    @GetMapping(path = "/allAwardingsHistory/id{idUser}")
    public ResponseEntity<List<AwardingHistoryDto>> getAllAwardingsOfAnUser(@PathVariable int idUser) throws ClassNotFoundException {
        return ResponseEntity.ok(awardingHistoryService.getAllAwardingsOfAnUser(idUser));
    }

    @GetMapping(path = "/allAwardingsHistory/year{year}")
    public ResponseEntity<List<Award>> getAllAwardingsOfTheYear(@PathVariable String year) throws ClassNotFoundException {
        return ResponseEntity.ok(awardingHistoryService.getAllAwardingsOfTheYear(year));
    }

    @PostMapping(path = "/createAwardingHistory")
    public ResponseEntity<String> createAwardingHistory(@RequestBody AwardingHistoryDto awardingHistoryDto) throws ClassNotFoundException {
        awardingHistoryService.createAwardingHistory(awardingHistoryDto);
        return ResponseEntity.ok("Awarding History created");
    }

    @PutMapping(path = "/updateAwardingHistory/id{idAwardingHistory}")
    public ResponseEntity<String> updateAwardingHistory(@RequestBody AwardingHistoryDto awardingHistoryDto, @PathVariable int idAwardingHistory) throws ClassNotFoundException {
        awardingHistoryService.updateAwardingHistory(idAwardingHistory, awardingHistoryDto);
        return ResponseEntity.ok("Awarding History updated");
    }

    @DeleteMapping(path = "/deleteAwardingHistory/id{idAwardingHistory}")
    public ResponseEntity<String> deleteAwardingHistory(@PathVariable int idAwardingHistory){
        awardingHistoryService.deleteAwardingHistory(idAwardingHistory);
        return ResponseEntity.ok("Awarding History deleted");
    }
}

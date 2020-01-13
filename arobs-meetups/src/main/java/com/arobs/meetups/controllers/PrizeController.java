package com.arobs.meetups.controllers;

import com.arobs.meetups.service.prize.PrizeService;
import com.arobs.meetups.service.prize.PrizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prizes")
public class PrizeController {
    @Autowired
    PrizeService prizeService;

    @GetMapping(path = "/id{idPrize}")
    public ResponseEntity<PrizeDto> findPrizeById(@PathVariable int idPrize) throws ClassNotFoundException {
        return ResponseEntity.ok(prizeService.findById(idPrize));
    }

    @GetMapping (path = "/allPrizes")
    public ResponseEntity<List<PrizeDto>> getAllPrizes () throws ClassNotFoundException{
        return ResponseEntity.ok(prizeService.getAll());
    }

    @PostMapping(path = "/createPrize{idPrize}")
    public ResponseEntity<String> createPrize(@RequestBody PrizeDto newPrizeDto) {
        prizeService.create(newPrizeDto);
        return ResponseEntity.ok("Prize created");
    }

    @PutMapping(path = "/updatePrize{idPrize}")
    public ResponseEntity<String> updatePrize (@RequestBody PrizeDto updatedPrizeDto, @PathVariable int idPrize){
        prizeService.update(idPrize, updatedPrizeDto);
        return ResponseEntity.ok("Prize updated");
    }

    @DeleteMapping(path = "/deletePrize{idPrize}")
    public ResponseEntity<String> deletePrize (@PathVariable int idPrize){
        prizeService.delete(idPrize);
        return ResponseEntity.ok("Prize deleted");
    }


}

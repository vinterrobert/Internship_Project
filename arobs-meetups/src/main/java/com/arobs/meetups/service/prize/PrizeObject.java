package com.arobs.meetups.service.prize;

import com.arobs.meetups.entities.Prize;
import com.arobs.meetups.repositories.PrizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrizeObject {

    @Autowired
    PrizeRepository prizeRepository;

    @Autowired
    PrizeMapper prizeMapper;

    public List<PrizeDto> getAll(){
        List<Prize> requestedPrizes = prizeRepository.getAll();
        List<PrizeDto> requestedPrizesDto = new ArrayList<>();
        if(!requestedPrizes.isEmpty()){
            for(Prize prize: requestedPrizes){
                requestedPrizesDto.add(prizeMapper.map(prize, PrizeDto.class));
            }
        }
        return requestedPrizesDto;
    }

    public PrizeDto finById(int idPrize){
        Prize requestedPrize = prizeRepository.findById(idPrize);
        return prizeMapper.map(requestedPrize, PrizeDto.class);
    }

    public void createPrize(PrizeDto newPrizeDto){
        Prize newPrize = prizeMapper.map(newPrizeDto, Prize.class);
        prizeRepository.createPrize(newPrize);
    }

    public void updatePrize (int idPrize, PrizeDto updatedPrizeDto){
        Prize requestedPrize = prizeRepository.findById(idPrize);
        Prize updatedPrize = prizeMapper.map(updatedPrizeDto, Prize.class);
        requestedPrize.setDescription(updatedPrize.getDescription());
        requestedPrize.setValue(updatedPrize.getValue());
        prizeRepository.update(requestedPrize);
    }

    public void deletePrize (int idPrize){
        Prize prizeToDelete = prizeRepository.findById(idPrize);
        prizeRepository.delete(prizeToDelete);
    }
}

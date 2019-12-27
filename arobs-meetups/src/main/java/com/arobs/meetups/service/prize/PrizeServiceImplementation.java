package com.arobs.meetups.service.prize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PrizeServiceImplementation implements IPrizeService {

    @Autowired
    PrizeObject prizeObject;

    @Override
    @Transactional
    public List<PrizeDto> getAll() {
        return prizeObject.getAll();
    }

    @Override
    @Transactional
    public PrizeDto findById(int idPrize) {
        return prizeObject.finById(idPrize);
    }

    @Override
    @Transactional
    public void createPrize(PrizeDto newPrizeDto) {
        prizeObject.createPrize(newPrizeDto);
    }

    @Override
    @Transactional
    public void updatePrize(int idPrize, PrizeDto updatedPrizeDto) {
        prizeObject.updatePrize(idPrize, updatedPrizeDto);
    }

    @Override
    @Transactional
    public void deletePrize(int idPrize) {
        prizeObject.deletePrize(idPrize);
    }
}

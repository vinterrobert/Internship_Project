package com.arobs.meetups.service.prize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PrizeServiceImplementation implements PrizeService {

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
    public void create(PrizeDto newPrizeDto) {
        prizeObject.create(newPrizeDto);
    }

    @Override
    @Transactional
    public void update(int idPrize, PrizeDto updatedPrizeDto) {
        prizeObject.update(idPrize, updatedPrizeDto);
    }

    @Override
    @Transactional
    public void delete(int idPrize) {
        prizeObject.delete(idPrize);
    }
}

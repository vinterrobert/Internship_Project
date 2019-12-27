package com.arobs.meetups.service.prize;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPrizeService {
    List<PrizeDto> getAll();
    PrizeDto findById(int idPrize);
    void createPrize(PrizeDto newPrizeDto);
    void updatePrize(int idPrize, PrizeDto updatedPrizeDto);
    void deletePrize(int idPrize);
}

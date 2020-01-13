package com.arobs.meetups.service.prize;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrizeService {
    List<PrizeDto> getAll();
    PrizeDto findById(int idPrize);
    void create(PrizeDto newPrizeDto);
    void update(int idPrize, PrizeDto updatedPrizeDto);
    void delete(int idPrize);
}

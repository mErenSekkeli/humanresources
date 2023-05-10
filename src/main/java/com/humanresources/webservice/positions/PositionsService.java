package com.humanresources.webservice.positions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionsService {
    PositionsRepository positionsRepository;

    @Autowired
    public PositionsService(PositionsRepository positionsRepository){
        this.positionsRepository = positionsRepository;
    }

    public List<Positions> getAllPositions(){
        return positionsRepository.findAll();
    }
}

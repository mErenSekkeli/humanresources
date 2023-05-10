package com.humanresources.webservice.positions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/1.0")
public class PositionsController {

    @Autowired
    PositionsService positionsService;

    @GetMapping("/getAllPositions")
    public List<Positions> getAllPositions(){
        return positionsService.getAllPositions();
    }
}

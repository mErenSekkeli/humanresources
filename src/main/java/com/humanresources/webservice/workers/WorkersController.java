package com.humanresources.webservice.workers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/1.0")
public class WorkersController {

    @Autowired
    WorkersService workersService;

    @GetMapping("/getWorkers")
    public List<Workers> getProjects(){
        return workersService.getAllWorkers();
    }

    @PostMapping("/addWorker")
    public Workers addProject(@RequestBody Workers worker){
        return workersService.addWorker(worker);
    }
}

package com.humanresources.webservice.workers;

import com.humanresources.webservice.dto.WorkerDto;
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
    public List<WorkerDto> getWorkers(){
        return workersService.getAllWorkers();
    }

    @PostMapping("/addWorker")
    public Workers addProject(@RequestBody Workers worker){
        return workersService.addWorker(worker);
    }

    @PostMapping("/getAllWorkerFromProject")
    public List<WorkerDto> getAllWorkerFromProject(@RequestParam Long projectId){
        return workersService.getAllWorkerFromProject(projectId);
    }
}

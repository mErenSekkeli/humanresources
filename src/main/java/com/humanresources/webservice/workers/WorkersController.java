package com.humanresources.webservice.workers;

import com.humanresources.webservice.dto.WorkerDto;
import com.humanresources.webservice.projects.Projects;
import com.humanresources.webservice.projects.ProjectsService;
import com.humanresources.webservice.relation.ProjectPosition;
import com.humanresources.webservice.relation.ProjectPositionService;
import com.humanresources.webservice.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/1.0")
public class WorkersController {

    @Autowired
    WorkersService workersService;

    @Autowired
    ProjectsService projectsService;

    @Autowired
    ProjectPositionService projectPositionService;

    @GetMapping("/getWorkers")
    public List<WorkerDto> getWorkers(){
        return workersService.getAllWorkers();
    }

    @PostMapping("/addWorker")
    public Workers addWorker(@RequestBody Workers worker){
        Workers newWorker = workersService.addWorker(worker);
        return newWorker;
    }

    @PostMapping("/findFreePosition")
    public ResponseEntity<?> findFreePosition(@RequestParam Long workerId){
        ArrayList<Projects> projects = (ArrayList<Projects>) projectsService.getAllActiveProjects();
        Workers worker = workersService.getWorkerById(workerId);
        for(Projects project : projects) {
            ArrayList<ProjectPosition> projectPositions = (ArrayList<ProjectPosition>) projectPositionService.getProjectPositionByProjectId(project.getId());
            for(ProjectPosition projectPosition : projectPositions){
                if(worker.getPositionId() == projectPosition.getPositionId()) {
                    int workerCount = projectPositionService.getWorkerPositionCount(project.getId(), projectPosition.getPositionId());
                    if(workerCount < projectPosition.getMaxWorker()){
                        workersService.changeUserProject(workerId, project.getId());
                        return ResponseEntity.ok(new GenericResponse("Worker is added to project"));
                    }
                }
            }
        }
        return ResponseEntity.ok(new GenericResponse("Worker is not added to project"));
    }

    @PostMapping("/getAllWorkerFromProject")
    public List<WorkerDto> getAllWorkerFromProject(@RequestParam Long projectId){
        return workersService.getAllWorkerFromProject(projectId);
    }

    @GetMapping("/getFreeWorkers")
    public List<Workers> getFreeWorkers(){
        return workersService.getFreeWorkers();
    }


}

package com.humanresources.webservice.projects;

import com.humanresources.webservice.dto.ProjectDto;
import com.humanresources.webservice.dto.WorkerDto;
import com.humanresources.webservice.relation.ProjectPosition;
import com.humanresources.webservice.relation.ProjectPositionService;
import com.humanresources.webservice.shared.GenericResponse;
import com.humanresources.webservice.workers.Workers;
import com.humanresources.webservice.workers.WorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/1.0")
public class ProjectsController {

    @Autowired
    ProjectsService projectsService;

    ProjectsRepository projectsRepository;

    @Autowired
    ProjectPositionService projectPositionService;

    @Autowired
    WorkersService workersService;

    @GetMapping("/getProjects")
    public List<Projects> getProjects(){
        return projectsService.getAllProjects();
    }

    @PostMapping("/addProject")
    public Projects addProject(@RequestBody Projects project){
        return projectsService.addProject(project);
    }

    @PostMapping("/finishProject")
    public ArrayList<WorkerDto> finishProject(@RequestParam Long projectId){
        projectsService.finishProject(projectId);
        ArrayList<WorkerDto> willDeleteWorkers = new ArrayList<>();
        ArrayList<Projects> allActiveProjects = (ArrayList<Projects>) projectsService.getAllActiveProjects();
        ArrayList<WorkerDto> oldWorkers = (ArrayList<WorkerDto>) workersService.getAllWorkerFromProject(projectId);
        for(WorkerDto worker : oldWorkers){
            boolean isWorkerChanged = false;
            for(Projects project : allActiveProjects){
                ArrayList<ProjectPosition> projectPositions = (ArrayList<ProjectPosition>) projectPositionService.getProjectPositionByProjectId(project.getId());
                for(ProjectPosition projectPosition : projectPositions){
                    if(worker.positionId == projectPosition.getPositionId()) {
                        int workerCount = projectPositionService.getWorkerPositionCount(project.getId(), projectPosition.getPositionId());
                        if(workerCount < projectPosition.getMaxWorker()){
                            workersService.changeUserProject(worker.id, project.getId());
                            isWorkerChanged = true;
                        }
                    }
                }
            }
            if(!isWorkerChanged){
                willDeleteWorkers.add(worker);
            }
        }

        for(WorkerDto worker : willDeleteWorkers){
            workersService.deleteWorker(worker.id);
        }

        return willDeleteWorkers;
    }

    @PostMapping("/getExpiredProjects")
    public List<ProjectDto> getExpiredProjects(){
        List<Projects> projects = projectsService.getExpiredProjects();
        List<ProjectDto> projectDtos = new ArrayList<>();

        for (Projects project : projects) {
            Long managerId = projectsService.getManagerId(project.getId());
            Workers manager = workersService.getWorkerById(managerId);

            ProjectDto projectDto = new ProjectDto(project, manager);
            projectDtos.add(projectDto);
        }
        return projectDtos;
    }

    @PostMapping("/getManagerProject")
    public ResponseEntity<?> getManagerProject(@RequestParam Long projectId){
        Long managerId = projectsService.getManagerId(projectId);
        if(managerId == -1L){
            return ResponseEntity.ok(new GenericResponse("Project has no manager"));
        }
        return ResponseEntity.ok(workersService.getWorkerById(managerId));
    }

    @PostMapping("/getAllManager")
    public ResponseEntity<?> getAllManager(){
        return ResponseEntity.ok(workersService.getAllManager());
    }
}

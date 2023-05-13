package com.humanresources.webservice.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/1.0")
public class ProjectPositionController {

    @Autowired
    ProjectPositionService projectPositionService;

    @PostMapping("/addProjectPosition")
    public ProjectPosition addProjectPosition(@RequestBody ProjectPosition projectPosition){
        return projectPositionService.addProjectPosition(projectPosition);
    }

    @PostMapping("getProjectPositionByProjectId")
    public List<ProjectPosition> getProjectPositionByProjectId(@RequestParam Long projectId){
        return projectPositionService.getProjectPositionByProjectId(projectId);
    }

    @PostMapping("getAllProjectPositions")
    public Iterable<ProjectPosition> getAllProjectPositions(){
        return projectPositionService.getAllProjectPositions();
    }
}

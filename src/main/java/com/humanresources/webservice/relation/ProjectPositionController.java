package com.humanresources.webservice.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/1.0")
public class ProjectPositionController {

    @Autowired
    ProjectPositionService projectPositionService;

    @PostMapping("/addProjectPosition")
    public ProjectPosition addProjectPosition(ProjectPosition projectPosition){
        return projectPositionService.addProjectPosition(projectPosition);
    }

    @PostMapping("getProjectPositionByProjectId")
    public ProjectPosition getProjectPositionByProjectId(Long projectId){
        return projectPositionService.getProjectPositionByProjectId(projectId);
    }
}

package com.humanresources.webservice.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/1.0")
public class ProjectsController {

    @Autowired
    ProjectsService projectsService;

    @GetMapping("/getProjects")
    public List<Projects> getProjects(){
        return projectsService.getAllProjects();
    }

    @PostMapping("/addProject")
    public Projects addProject(@RequestBody Projects project){
        return projectsService.addProject(project);
    }
}

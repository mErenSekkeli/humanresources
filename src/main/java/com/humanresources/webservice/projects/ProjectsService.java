package com.humanresources.webservice.projects;

import com.humanresources.webservice.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {
    ProjectsRepository projectsRepository;

    @Autowired
    public ProjectsService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }


    public List<Projects> getAllProjects(){
        return projectsRepository.findAll();
    }

   public Projects addProject(Projects projects){
        return projectsRepository.save(projects);
   }
}

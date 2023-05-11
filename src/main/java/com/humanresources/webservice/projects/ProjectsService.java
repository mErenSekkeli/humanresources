package com.humanresources.webservice.projects;

import com.humanresources.webservice.shared.GenericResponse;
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

    public Iterable<Projects> getAllActiveProjects(){
        return projectsRepository.findAllByActive();
    }

   public Projects addProject(Projects projects){
        return projectsRepository.save(projects);
   }

    public Projects getProjectById(Long projectId){
         return projectsRepository.findById(projectId).get();
    }

   public void finishProject(Long projectId){
       Projects project = projectsRepository.findById(projectId).get();
       project.setActive(false);
       projectsRepository.save(project);
   }


}

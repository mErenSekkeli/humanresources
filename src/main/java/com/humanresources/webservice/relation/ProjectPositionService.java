package com.humanresources.webservice.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectPositionService {

    @Autowired
    ProjectPositionRepository projectPositionRepository;

    public ProjectPosition addProjectPosition(ProjectPosition projectPosition){
        return projectPositionRepository.save(projectPosition);
    }

    public ProjectPosition getProjectPositionByProjectId(Long projectId){
        return projectPositionRepository.findByProjectId(projectId);
    }
}

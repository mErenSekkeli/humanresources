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

    public Iterable<ProjectPosition> getProjectPositionByProjectId(Long projectId){
        return projectPositionRepository.findAllByProjectId(projectId);
    }

    public Iterable<ProjectPosition> getAllProjectPositions(){
        return projectPositionRepository.findAll();
    }

    public int getWorkerPositionCount(Long projectId, Long positionId){
        return projectPositionRepository.countAllByWorkerId(projectId, positionId);
    }
}

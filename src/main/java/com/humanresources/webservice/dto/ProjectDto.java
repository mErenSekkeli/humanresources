package com.humanresources.webservice.dto;

import com.humanresources.webservice.projects.Projects;
import com.humanresources.webservice.workers.Workers;

import java.sql.Date;

public class ProjectDto {

    public long projectId;
    public String projectName;
    public Date projectPlannedStartDate;
    public Date projectPlannedDeliveryDate;
    public boolean projectActive;
    public boolean projectValid;
    public long projectManagerId;
    public String projectManagerName;
    public String projectManagerSurname;

    public ProjectDto(Projects project, Workers worker) {
        this.projectId = project.getId();
        this.projectName = project.getName();
        this.projectPlannedStartDate = project.getPlannedStartDate();
        this.projectPlannedDeliveryDate = project.getPlannedDeliveryDate();
        this.projectActive = project.isActive();
        this.projectValid = project.isValid();
        this.projectManagerId = project.getManagerId();
        this.projectManagerName = worker.getName();
        this.projectManagerSurname = worker.getSurname();
    }
}

package com.humanresources.webservice.relation;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProjectPosition {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "projectId", nullable = false)
    private long projectId;

    @Column(name = "positionId", nullable = false)
    private long positionId;

    @Column(name = "minWorker", nullable = false)
    private int minWorker;

    @Column(name = "maxWorker", nullable = false)
    private int maxWorker;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public int getMinWorker() {
        return minWorker;
    }

    public void setMinWorker(int minWorker) {
        this.minWorker = minWorker;
    }

    public int getMaxWorker() {
        return maxWorker;
    }

    public void setMaxWorker(int maxWorker) {
        this.maxWorker = maxWorker;
    }
}

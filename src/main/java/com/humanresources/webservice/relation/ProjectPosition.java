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
}

package com.humanresources.webservice.projects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Projects {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "plannedStartDate", nullable = false)
    private Date plannedStartDate;
    @Column(name = "plannedDeliveryDate", nullable = false)
    private Date plannedDeliveryDate;
    @Column(name = "active", nullable = false)
    private boolean active;
    @Column(name = "valid", nullable = false)
    private boolean valid;
    @Column(name = "managerId", nullable = false)
    private long managerId;

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}

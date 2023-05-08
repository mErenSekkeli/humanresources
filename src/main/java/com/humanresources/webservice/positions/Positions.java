package com.humanresources.webservice.positions;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Positions {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
}

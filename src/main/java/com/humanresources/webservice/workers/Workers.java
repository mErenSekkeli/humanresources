package com.humanresources.webservice.workers;


import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Workers {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "ssn", nullable = false)
    private String ssn;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "telNo", nullable = false)
    private String telNo;
    @Column(name = "salary", nullable = false)
    private int salary;
    @Column(name = "positionId", nullable = false)
    private long positionId;
    @Column(name = "projectId")
    private long projectId;
    @Column(name = "recruitmentDate", nullable = false)
    private Date recruitmentDate;
    @Column(name = "termiantionDate", nullable = false)
    private Date termiantionDate;
    @Column(name = "compensationAmount", nullable = false)
    private int compensationAmount;

}

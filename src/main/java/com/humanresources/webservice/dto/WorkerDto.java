package com.humanresources.webservice.dto;

import com.humanresources.webservice.positions.Positions;
import com.humanresources.webservice.workers.Workers;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

public class WorkerDto {
        public long id;
        public String name;
        public String surname;
        public String ssn;
        public String address;
        public String telNo;
        public int salary;
        public String position;
        public Date recruitmentDate;
        public Date termiantionDate;
        public int compensationAmount;

        public WorkerDto(Workers worker, Positions positions){
                this.id = worker.getId();
                this.name = worker.getName();
                this.surname = worker.getSurname();
                this.ssn = worker.getSsn();
                this.address = worker.getAddress();
                this.telNo = worker.getTelNo();
                this.salary = worker.getSalary();
                this.position = positions.getName();
                this.recruitmentDate = worker.getRecruitmentDate();
                this.termiantionDate = worker.getTermiantionDate();
                this.compensationAmount = worker.getCompensationAmount();
        }

}

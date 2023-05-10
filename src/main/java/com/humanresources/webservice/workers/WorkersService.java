package com.humanresources.webservice.workers;

import com.humanresources.webservice.workers.WorkersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkersService {
    WorkersRepository workersRepository;

    @Autowired
    public WorkersService(WorkersRepository workersRepository) {
        this.workersRepository = workersRepository;
    }


    public List<Workers> getAllWorkers(){
        return workersRepository.findAll();
    }

    public Workers addWorker(Workers worker){
        return workersRepository.save(worker);
    }
}

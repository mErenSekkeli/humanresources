package com.humanresources.webservice.workers;

import com.humanresources.webservice.dto.WorkerDto;
import com.humanresources.webservice.positions.Positions;
import com.humanresources.webservice.positions.PositionsService;
import com.humanresources.webservice.workers.WorkersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkersService {
    WorkersRepository workersRepository;

    @Autowired
    PositionsService positionsService;

    @Autowired
    public WorkersService(WorkersRepository workersRepository) {
        this.workersRepository = workersRepository;
    }


    public List<WorkerDto> getAllWorkers(){
        List<Workers>  workers = workersRepository.findAll();
        List<Positions> positions = positionsService.getAllPositions();
        List<WorkerDto> workerDtos = new ArrayList<>();

        for (Workers worker: workers) {
            Positions positions1 = null;

            for (Positions element: positions) {
                if(element.getId() == worker.getPositionId()){
                    positions1 = element;
                    break;
                }
            }

            if(positions1 != null)
                workerDtos.add(new WorkerDto(worker,positions1));

        }

        return workerDtos;
    }

    public List<WorkerDto> getAllWorkerFromProject(long projectId) {
        List<Workers>  workers = workersRepository.findAll();
        List<Positions> positions = positionsService.getAllPositions();
        List<WorkerDto> workerDtos = new ArrayList<>();

        for (Workers worker: workers) {
            Positions positions1 = null;

            for (Positions element: positions) {
                if(element.getId() == worker.getPositionId()){
                    positions1 = element;
                    break;
                }
            }

            if(positions1 != null && worker.getProjectId() == projectId)
                workerDtos.add(new WorkerDto(worker,positions1));

        }

        return workerDtos;
    }

    public void changeUserProject(Long workerId, Long projectId){
        Workers worker = workersRepository.findById(workerId).get();
        worker.setProjectId(projectId);
        workersRepository.save(worker);
    }

    public void deleteWorker(Long workerId){
        workersRepository.deleteById(workerId);
    }

    public Workers addWorker(Workers worker){
        return workersRepository.save(worker);
    }
}

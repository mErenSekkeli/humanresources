package com.humanresources.webservice;

import com.humanresources.webservice.dto.WorkerDto;
import com.humanresources.webservice.positions.Positions;
import com.humanresources.webservice.positions.PositionsController;
import com.humanresources.webservice.positions.PositionsRepository;
import com.humanresources.webservice.projects.Projects;
import com.humanresources.webservice.projects.ProjectsController;
import com.humanresources.webservice.projects.ProjectsRepository;
import com.humanresources.webservice.relation.ProjectPosition;
import com.humanresources.webservice.relation.ProjectPositionController;
import com.humanresources.webservice.shared.GenericResponse;
import com.humanresources.webservice.workers.Workers;
import com.humanresources.webservice.workers.WorkersController;
import com.humanresources.webservice.workers.WorkersRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class WebserviceApplicationTests {

	@Autowired
	WorkersRepository workersRepository;

	@Autowired
	WorkersController workersController;

	@Autowired
	ProjectsController projectsController;

	@Autowired
	ProjectsRepository projectsRepository;

	@Autowired
	PositionsRepository positionsRepository;

	@Autowired
	PositionsController positionsController;

	@Autowired
	ProjectPositionController projectPositionController;


	@Test
	void getAllWorkers() {
		List<Workers> allWorkers = workersRepository.findAll();

		assertNotEquals(0, allWorkers.size());
	}

	@Test
	void getAllProjects() {
		List<Projects> allProjects = projectsRepository.findAll();

		assertNotEquals(0, allProjects.size());
	}

	@Test
	void addNewWorker() {
		Workers worker = new Workers();
		worker.setName("Test");
		worker.setSurname("Test");
		worker.setSsn("12345678901");
		worker.setTelNo("123456789");
		worker.setAddress("Test");
		worker.setAccountingUrl("Test");
		worker.setPositionId(2);
		worker.setRecruitmentDate(new Date());
		worker.setSalary(1000);

		List<Workers> oldAllWorkers = workersRepository.findAll();
		workersController.addWorker(worker);
		List<Workers> newAllWorkers = workersRepository.findAll();

		assertEquals(oldAllWorkers.size() + 1, newAllWorkers.size());
	}

	@Test
	void findProjectToNewWorker() {
		List<Workers> allWorkers = workersRepository.findAll();
		Workers lastWorker = allWorkers.get(allWorkers.size() - 1);
		workersController.findFreePosition(lastWorker.getId());
		Workers newLastWorker = workersRepository.findById(lastWorker.getId()).get();
		assertNotEquals(0, newLastWorker.getProjectId());

	}

	@Test
	void addNewProject() {
		Projects project = new Projects();
		project.setName("Test Project");
		project.setPlannedStartDate(new java.sql.Date(new Date().getTime()));
		project.setPlannedDeliveryDate(new java.sql.Date(new Date().getTime()));
		project.setActive(true);
		project.setValid(false);
		project.setManagerId(702);

		List<Projects> oldAllProjects = projectsRepository.findAll();
		projectsController.addProject(project);
		List<Projects> newAllProjects = projectsRepository.findAll();

		assertEquals(oldAllProjects.size() + 1, newAllProjects.size());
	}

	@Test
	void getAllPositions() {
		List<Positions> allPositions = positionsRepository.findAll();

		assertNotEquals(0, allPositions.size());
	}

	@Test
	void savePosition() {
		List<Positions> allPositions = positionsRepository.findAll();

		Positions positions = new Positions();
		positions.setName("Test");


		List<Positions> oldAllPositions = positionsRepository.findAll();
		Positions positions1 = positionsRepository.save(positions);
		List<Positions> newAllPositions = positionsRepository.findAll();

		positionsRepository.deleteById(positions1.getId());

		assertEquals(oldAllPositions.size() + 1, newAllPositions.size());
	}

	@Test
	void getFreeWorkers(){
		List<Workers> freeWorkers = workersController.getFreeWorkers();

		for(Workers workers: freeWorkers){
			assertEquals(0, workers.getProjectId());
		}
	}


	@Test
	void checkManagerId(){
		List<Projects> projectsList = projectsController.getProjects();

		for(Projects projects: projectsList){
			assertNotEquals(0, projects.getManagerId());
		}
	}

	@Test
	void getWorkerPositionCount(){
		Random random = new Random();
		Iterable<Positions> positionsList = positionsController.getAllPositions();

		List<Projects> projectsList = projectsController.getProjects();

		int randomNumber = random.nextInt(projectsList.size() - 0 + 1) + 0;
		Projects projects = projectsList.get(randomNumber);

		int positionsSize = 0;
		Iterator<Positions> iterator = positionsList.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			positionsSize++;
		}

		int positionsOfProjectSize = projectPositionController.getProjectPositionByProjectId(projects.getId()).size();

		assertTrue(positionsSize >= positionsOfProjectSize);
	}




}

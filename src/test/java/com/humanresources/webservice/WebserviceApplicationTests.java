package com.humanresources.webservice;

import com.humanresources.webservice.projects.Projects;
import com.humanresources.webservice.projects.ProjectsController;
import com.humanresources.webservice.projects.ProjectsRepository;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

	@Test
	void getAllWorkers() {
		List<Workers> allWorkers = workersRepository.findAll();

		assertNotEquals(0, allWorkers.size());
	}

	@Test
	void getAllProjects() {
		List<Workers> allWorkers = workersRepository.findAll();

		assertNotEquals(0, allWorkers.size());
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
		worker.setPositionId(4);
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



}

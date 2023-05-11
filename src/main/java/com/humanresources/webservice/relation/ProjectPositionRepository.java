package com.humanresources.webservice.relation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectPositionRepository extends JpaRepository<ProjectPosition, Long> {

    ProjectPosition findByProjectId(Long projectId);
}

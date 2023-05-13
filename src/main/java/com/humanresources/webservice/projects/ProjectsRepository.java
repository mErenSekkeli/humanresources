package com.humanresources.webservice.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM projects WHERE active = true")
    Iterable<Projects> findAllByActive();

    @Query(nativeQuery = true, value = "SELECT * FROM projects WHERE active = true AND planned_delivery_date < NOW()")
    List<Projects> getExpiredProjects();

    @Query(nativeQuery = true, value = "SELECT manager_id FROM projects WHERE id = :projectId")
    Long getManagerId(@Param("projectId") Long projectId);
}

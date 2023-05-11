package com.humanresources.webservice.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM projects WHERE active = ?1")
    Iterable<Projects> findAllByActive();
}

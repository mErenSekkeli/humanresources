package com.humanresources.webservice.relation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProjectPositionRepository extends JpaRepository<ProjectPosition, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM project_position WHERE project_id =:projectId")
    List<ProjectPosition> findAllByProjectId(@RequestParam Long projectId);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM workers WHERE project_id =:projectId AND position_id =:positionId")
    int countAllByWorkerId(@RequestParam Long projectId, @RequestParam Long positionId);
}

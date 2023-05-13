package com.humanresources.webservice.workers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkersRepository extends JpaRepository<Workers, Long> {

    @Query(nativeQuery = true, value = "select * from workers where project_id=0")
    List<Workers> findFreeWorkers();
}

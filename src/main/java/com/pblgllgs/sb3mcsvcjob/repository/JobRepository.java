package com.pblgllgs.sb3mcsvcjob.repository;

import com.pblgllgs.sb3mcsvcjob.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}

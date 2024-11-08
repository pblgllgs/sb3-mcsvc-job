package com.pblgllgs.sb3mcsvcjob.service;
/*
 *
 * @author pblgl
 * Created on 21-10-2024
 *
 */

import com.pblgllgs.sb3mcsvcjob.dto.JobDTO;
import com.pblgllgs.sb3mcsvcjob.model.Job;

import java.util.List;

public interface JobService {

    List<JobDTO> findAllJobs();
    void createJob(Job job);
    JobDTO findJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJob(Long id, Job job);
}

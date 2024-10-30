package com.pblgllgs.sb3mcsvcjob.job.impl;
/*
 *
 * @author pblgl
 * Created on 21-10-2024
 *
 */

import com.pblgllgs.sb3mcsvcjob.job.Job;
import com.pblgllgs.sb3mcsvcjob.job.JobRepository;
import com.pblgllgs.sb3mcsvcjob.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Optional<Job> findJobById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    @Override
    public boolean updateJob(Long id, Job jobToUpdate) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setDescription(jobToUpdate.getDescription());
            job.setLocation(jobToUpdate.getLocation());
            job.setTitle(jobToUpdate.getTitle());
            job.setMaxSalary(jobToUpdate.getMaxSalary());
            job.setMinSalary(jobToUpdate.getMinSalary());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}

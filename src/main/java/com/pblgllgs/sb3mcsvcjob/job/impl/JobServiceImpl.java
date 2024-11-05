package com.pblgllgs.sb3mcsvcjob.job.impl;
/*
 *
 * @author pblgl
 * Created on 21-10-2024
 *
 */

import com.pblgllgs.sb3mcsvcjob.dto.JobDTO;
import com.pblgllgs.sb3mcsvcjob.external.Company;
import com.pblgllgs.sb3mcsvcjob.external.Review;
import com.pblgllgs.sb3mcsvcjob.job.Job;
import com.pblgllgs.sb3mcsvcjob.job.JobRepository;
import com.pblgllgs.sb3mcsvcjob.job.JobService;
import com.pblgllgs.sb3mcsvcjob.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private RestTemplate restTemplate;

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobDTO> findAllJobs() {
        List<Job> allJobs = jobRepository.findAll();
        return allJobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JobDTO convertToDto(Job job) {
        Company company = restTemplate.getForObject(
                "http://COMPANY-SERVICE:8081/company/" + job.getCompanyId(),
                Company.class
        );
        ResponseEntity<List<Review>> response = restTemplate.exchange(
                "http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
                });

        List<Review> reviews = response.getBody();


        return JobMapper.mapToJobWithCompanyDTO(job, company,reviews);
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO findJobById(Long id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        return convertToDto(optionalJob.get());
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

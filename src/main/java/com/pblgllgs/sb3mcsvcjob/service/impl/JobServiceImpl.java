package com.pblgllgs.sb3mcsvcjob.service.impl;
/*
 *
 * @author pblgl
 * Created on 21-10-2024
 *
 */

import com.pblgllgs.sb3mcsvcjob.clients.CompanyClient;
import com.pblgllgs.sb3mcsvcjob.clients.ReviewClient;
import com.pblgllgs.sb3mcsvcjob.dto.JobDTO;
import com.pblgllgs.sb3mcsvcjob.external.Company;
import com.pblgllgs.sb3mcsvcjob.external.Review;
import com.pblgllgs.sb3mcsvcjob.mapper.JobMapper;
import com.pblgllgs.sb3mcsvcjob.model.Job;
import com.pblgllgs.sb3mcsvcjob.repository.JobRepository;
import com.pblgllgs.sb3mcsvcjob.service.JobService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private RestTemplate restTemplate;

    private final JobRepository jobRepository;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    int attempts = 0;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
//    @CircuitBreaker(
//            name="companyBreaker",
//            fallbackMethod = "companyBreakerFallback"
//    )
//    @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAllJobs() {
        System.out.println("attempt: " + ++attempts);
        List<Job> allJobs = jobRepository.findAll();
        return allJobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JobDTO convertToDto(Job job) {
        ResponseEntity<Company> company = companyClient.getCompany(job.getCompanyId());
        ResponseEntity<List<Review>> allReviewByCompany = reviewClient.getAllReviewByCompany(company.getBody().getId());

        List<Review> reviews = allReviewByCompany.getBody();

        return JobMapper.mapToJobWithCompanyDTO(job, company.getBody(), reviews);
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

    public List<String> companyBreakerFallback(Exception e) {
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;
    }
}

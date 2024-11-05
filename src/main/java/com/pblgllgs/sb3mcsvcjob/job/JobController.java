package com.pblgllgs.sb3mcsvcjob.job;
/*
 *
 * @author pblgl
 * Created on 21-10-2024
 *
 */

import com.pblgllgs.sb3mcsvcjob.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping()
    public ResponseEntity<List<JobDTO>> findAllJobs() {
        return new ResponseEntity<>(jobService.findAllJobs(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> findById(@PathVariable Long id) {
        JobDTO jobWithCompanyDTO = jobService.findJobById(id);
        if (jobWithCompanyDTO != null) {
            return new ResponseEntity<>(jobWithCompanyDTO, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if (deleted) {
            return new ResponseEntity<>("job deleted successfully", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("job not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job) {
        boolean updated = jobService.updateJob(id,job);
        if (updated) {
            return new ResponseEntity<>("job successfully updated",HttpStatus.OK);
        }
        return new ResponseEntity<>("job cant be updated", HttpStatus.NOT_FOUND);
    }
}

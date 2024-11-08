package com.pblgllgs.sb3mcsvcjob.mapper;
/*
 *
 * @author pblgl
 * Created on 05-11-2024
 *
 */

import com.pblgllgs.sb3mcsvcjob.dto.JobDTO;
import com.pblgllgs.sb3mcsvcjob.external.Company;
import com.pblgllgs.sb3mcsvcjob.external.Review;
import com.pblgllgs.sb3mcsvcjob.model.Job;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobWithCompanyDTO(Job job, Company company, List<Review> reviews) {
        JobDTO jobWithCompanyDTO = new JobDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setReview(reviews);
        return jobWithCompanyDTO;
    }
}

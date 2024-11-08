package com.pblgllgs.sb3mcsvcjob.clients;
/*
 *
 * @author pblgl
 * Created on 07-11-2024
 *
 */


import com.pblgllgs.sb3mcsvcjob.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEW")
public interface ReviewClient {

    @GetMapping("/reviews?companyId=")
    ResponseEntity<List<Review>> getAllReviewByCompany(
            @RequestParam Long companyId
    );
}

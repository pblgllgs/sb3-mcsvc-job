package com.pblgllgs.sb3mcsvcjob.clients;
/*
 *
 * @author pblgl
 * Created on 07-11-2024
 *
 */

import com.pblgllgs.sb3mcsvcjob.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY", url = "${company-service.url}")
public interface CompanyClient {

    @GetMapping("/companies/{id}")
    ResponseEntity<Company> getCompany(@PathVariable long id);

}

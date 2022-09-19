package com.fawry.auditing_v1.resources;

import com.fawry.auditing_v1.models.Application;
import com.fawry.auditing_v1.sevices.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/applications")
public class ApplicationResource {
    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<Application>> findAplications(){
        return new ResponseEntity(applicationService.findApplications(), HttpStatus.OK);
    }
}

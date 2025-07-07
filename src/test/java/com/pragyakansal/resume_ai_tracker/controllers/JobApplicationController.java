package com.pragyakansal.resume_ai_tracker.controllers;

import com.pragyakansal.resume_ai_tracker.entities.JobApplication;
import com.pragyakansal.resume_ai_tracker.exceptions.AlreadyExistsException;
import com.pragyakansal.resume_ai_tracker.exceptions.NotFoundException;
import com.pragyakansal.resume_ai_tracker.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobApplicationController {

    private JobApplicationService jobApplicationService;

    @Autowired
    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/job-applications")
    public List<JobApplication> getAllJobApplications() throws NotFoundException {
        return jobApplicationService.getAllJobApplications();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/job-applications/{id}")
    public JobApplication getSpecificJobApplication(@PathVariable("id") Long applicationId) throws NotFoundException {
        return jobApplicationService.getSpecificJobApplication(applicationId);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/job-applications")
    public JobApplication createJobApplication(@RequestBody JobApplication jobApplication) throws AlreadyExistsException {
        return jobApplicationService.addJobApplication(jobApplication);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/job-applications/{id}")
    public JobApplication updateJobApplication(@PathVariable("id") Long applicationId) throws NotFoundException {
        return jobApplicationService.updateJobApplication(applicationId);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/job-applications/{id}")
    public void deleteJobApplication(@PathVariable("id") Long applicationId) throws NotFoundException {
        jobApplicationService.deleteJobApplication(applicationId);
    }



}

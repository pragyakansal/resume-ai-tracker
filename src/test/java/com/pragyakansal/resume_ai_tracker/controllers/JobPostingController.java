package com.pragyakansal.resume_ai_tracker.controllers;

import com.pragyakansal.resume_ai_tracker.entities.JobApplication;
import com.pragyakansal.resume_ai_tracker.entities.JobPosting;
import com.pragyakansal.resume_ai_tracker.exceptions.AlreadyExistsException;
import com.pragyakansal.resume_ai_tracker.exceptions.NotFoundException;
import com.pragyakansal.resume_ai_tracker.services.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobPostingController {
    private JobPostingService jobPostingService;

    @Autowired
    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/job-postings")
    public List<JobPosting> getAllJobPostings() throws NotFoundException {
        return jobPostingService.getAllJobPostings();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/job-postings/{id}")
    public JobPosting getSpecificJobPosting(@PathVariable("id") Long postingId) throws NotFoundException {
        return jobPostingService.getSpecificJobPosting(postingId);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/job-applications")
    public JobPosting createJobPosting(@RequestBody JobPosting jobPosting) throws AlreadyExistsException {
        return jobPostingService.addJobPosting(jobPosting);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/job-applications/{id}")
    public JobPosting updateJobApplication(@PathVariable("id") Long postingId) throws NotFoundException {
        return jobPostingService.updateJobPosting(postingId);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/job-applications/{id}")
    public void deleteJobApplication(@PathVariable("id") Long postingId) throws NotFoundException {
        jobPostingService.deleteJobPosting(postingId);
    }


}

package com.pragyakansal.resume_ai_tracker.services;


import com.pragyakansal.resume_ai_tracker.entities.JobPosting;

import java.util.List;

public interface JobPostingService {
    List<JobPosting> getAllJobPostings();
    JobPosting getSpecificJobPosting(Long postingId);
    JobPosting addJobPosting(JobPosting jobPosting);
    JobPosting updateJobPosting(Long postingId);
    void deleteJobPosting(Long postingId);
}

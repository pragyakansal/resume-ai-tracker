package com.pragyakansal.resume_ai_tracker.services;

import com.pragyakansal.resume_ai_tracker.entities.JobApplication;

import java.util.List;

public interface JobApplicationService {
    List<JobApplication> getAllJobApplications();
    JobApplication getSpecificJobApplication(Long applicationId);
    JobApplication addJobApplication(JobApplication jobApplication);
    void deleteJobApplication(Long applicationId);
    JobApplication updateJobApplication(Long applicationId);
}

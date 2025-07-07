package com.pragyakansal.resume_ai_tracker.services;

import com.pragyakansal.resume_ai_tracker.entities.JobApplication;
import com.pragyakansal.resume_ai_tracker.exceptions.AlreadyExistsException;
import com.pragyakansal.resume_ai_tracker.exceptions.BadParameterException;
import com.pragyakansal.resume_ai_tracker.exceptions.NotFoundException;
import com.pragyakansal.resume_ai_tracker.repositories.JobApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }
    @Override
    public List<JobApplication> getAllJobApplications() {
        List<JobApplication> allJobApps = jobApplicationRepository.findAll();
        if (allJobApps.isEmpty()) {
            throw new NotFoundException("No job applications could be found for the user: ");
        }
        return allJobApps;
    }

    @Override
    public JobApplication getSpecificJobApplication(Long applicationId) {
        JobApplication jobApp = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("No job applications with the id: " + applicationId + " could be found."));
        return jobApp;
    }

    @Override
    // research why we need this annotation
    @Transactional
    public JobApplication addJobApplication(JobApplication newJobApp) {
        Optional<JobApplication> existingJobApp = jobApplicationRepository.findByUserAndCompanyAndPositionAndLocation(
                newJobApp.getUser(),
                newJobApp.getCompany(),
                newJobApp.getPosition(),
                newJobApp.getLocation()
        );

        if (existingJobApp.isPresent()) {
            JobApplication existing = existingJobApp.get();
            throw new AlreadyExistsException("A job application with the position: " + existing.getPosition() +
                    ", the company: " + existing.getCompany() + ", and the location: " + existing.getLocation() +
                    " already exists. Please try again and add a new posting with a new position, company, and location.");
        }

        validateJobApplicationFields(newJobApp);
        return jobApplicationRepository.save(newJobApp);
    }

    private void validateJobApplicationFields(JobApplication newJobApp) {
        if (newJobApp.getPosition() == null || newJobApp.getPosition().trim().isEmpty()) {
            throw new BadParameterException("The position field should not be null or empty.");
        }

        if (newJobApp.getCompany() == null || newJobApp.getCompany().trim().isEmpty()) {
            throw new BadParameterException("The company field should not be null or empty.");
        }

        if (newJobApp.getAppliedAt() == null || newJobApp.getAppliedAt().isAfter(LocalDateTime.now())) {
            throw new BadParameterException("The applied date should not be null and cannot be in the future.");
        }

        if (newJobApp.getUser() == null) {
            throw new BadParameterException("A user reference is required.");
        }
    }

    @Override
    @Transactional
    public JobApplication updateJobApplication(Long applicationId) {
        JobApplication jobAppToBeUpdated = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("No job applications with the id: " + applicationId + " could be found. " +
                        "Please update a job application that already exists."));
        jobApplicationRepository.save(jobAppToBeUpdated);
        return jobAppToBeUpdated;
    }

    @Override
    @Transactional
    public void deleteJobApplication(Long applicationId) {
        JobApplication jobAppToBeDeleted = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("No job applications with the id: " + applicationId + " could be found. " +
                        "Please delete a job application that already exists."));
        jobApplicationRepository.delete(jobAppToBeDeleted);
    }
}

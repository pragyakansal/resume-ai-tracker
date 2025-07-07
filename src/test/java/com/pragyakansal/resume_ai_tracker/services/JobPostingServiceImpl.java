package com.pragyakansal.resume_ai_tracker.services;

import com.pragyakansal.resume_ai_tracker.entities.JobApplication;
import com.pragyakansal.resume_ai_tracker.entities.JobPosting;
import com.pragyakansal.resume_ai_tracker.exceptions.AlreadyExistsException;
import com.pragyakansal.resume_ai_tracker.exceptions.BadParameterException;
import com.pragyakansal.resume_ai_tracker.exceptions.NotFoundException;
import com.pragyakansal.resume_ai_tracker.repositories.JobPostingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPostingServiceImpl implements JobPostingService {

    private JobPostingRepository jobPostingRepository;

    @Autowired
    public JobPostingServiceImpl(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }

    @Override
    public List<JobPosting> getAllJobPostings() {
        List<JobPosting> allJobPostings = jobPostingRepository.findAll();
        if (allJobPostings.isEmpty()) {
            throw new NotFoundException("No job postings could be found.");
        }
        return allJobPostings;
    }

    @Override
    public JobPosting getSpecificJobPosting(Long postingId) {
        JobPosting jobPosting = jobPostingRepository.findById(postingId).
                orElseThrow(() -> new NotFoundException("No job postings with the id : " + postingId + " could be found."));
        return jobPosting;
    }

    @Override
    @Transactional
    public JobPosting addJobPosting(JobPosting newJobPosting) {
        Optional<JobPosting> existingJobPosting = jobPostingRepository.findByCompanyAndPositionAndLocation(
                newJobPosting.getCompany(),
                newJobPosting.getPosition(),
                newJobPosting.getLocation()
        );

        if (existingJobPosting.isPresent()) {
            throw new AlreadyExistsException("The job posting with the company: " + newJobPosting.getCompany() + ", the position: "
                    + newJobPosting.getPosition() + ", and the location: " + newJobPosting.getLocation() + " already exists." +
                    "Please try adding a new posting with a new company, position, and location.");
        }
        validateJobPostingFields(newJobPosting);
        return jobPostingRepository.save(newJobPosting);
    }

    private void validateJobPostingFields(JobPosting newJobPosting) {
        if (newJobPosting.getCompany() == null || newJobPosting.getCompany().trim().isEmpty()) {
            throw new BadParameterException("The company name should not be null or empty.");
        }
        if (newJobPosting.getPosition() == null || newJobPosting.getPosition().trim().isEmpty()) {
            throw new BadParameterException("The position name should not be null or empty.");
        }
        if (newJobPosting.getLocation() == null || newJobPosting.getLocation().trim().isEmpty()) {
            throw new BadParameterException("The location should not be null or empty.");
        }
    }

    @Override
    @Transactional
    public JobPosting updateJobPosting(Long postingId) {
        JobPosting jobPostingToBeUpdated = jobPostingRepository.findById(postingId)
                .orElseThrow(() -> new NotFoundException("No job applications with the id: " + postingId + " could be found. " +
                        "Please update a job application that already exists."));
        jobPostingRepository.save(jobPostingToBeUpdated);
        return jobPostingToBeUpdated;
    }

    @Override
    @Transactional
    public void deleteJobPosting(Long postingId) {
        JobPosting jobPostingToBeDeleted = jobPostingRepository.findById(postingId)
                .orElseThrow(() -> new NotFoundException("No job applications with the id: " + postingId + " could be found. " +
                        "Please update a job application that already exists."));
       jobPostingRepository.delete(jobPostingToBeDeleted);
    }

}

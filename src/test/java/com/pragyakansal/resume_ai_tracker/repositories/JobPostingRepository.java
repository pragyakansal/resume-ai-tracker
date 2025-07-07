package com.pragyakansal.resume_ai_tracker.repositories;


import com.pragyakansal.resume_ai_tracker.entities.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    Optional<JobPosting> findByCompanyAndPositionAndLocation(String company, String position, String location);

}

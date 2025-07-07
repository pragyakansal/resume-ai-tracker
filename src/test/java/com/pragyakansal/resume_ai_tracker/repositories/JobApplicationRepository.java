package com.pragyakansal.resume_ai_tracker.repositories;

import com.pragyakansal.resume_ai_tracker.entities.JobApplication;
import com.pragyakansal.resume_ai_tracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findAll();
    //JobApplication findById(Long id);
    Optional<JobApplication> findByUserAndCompanyAndPositionAndLocation(User user, String company, String position, String location);

}

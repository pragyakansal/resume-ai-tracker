package com.pragyakansal.resume_ai_tracker.entities;
import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String fileLink;

    @Column
    private String analysisResults;


}

import React, { useState, useEffect } from 'react';

const JobApplication = () => {
  const [jobDescription, setJobDescription] = useState('');
  const [companyName, setCompanyName] = useState('');
  const [companyLocation, setCompanyLocation] = useState('');
  const [jobPosition, setJobPosition] = useState('');
  const [requiredSkills, setRequiredSkills] = useState('');
  const [feedbackMessage, setFeedbackMessage] = useState('');
  const getJobApplication = () => {
    const url = "http://localhost:8080/job-applications/{id}";
    try {
      const response = await fetch(url);
      console.log("response: ", response);
      console.log("response data", response.formData);
      const data = await response.json();
      if (response.ok) {
        setJobDescription(data.jobDescription);
        setCompanyName(data.companyName);
        setCompanyLocation(data.companyLocation);
        setJobPosition(data.jobPosition);
        setRequiredSkills(data.requiredSkills);
      } else {
        setFeedbackMessage("The job application could not be retrieved.");
      }
    } catch (error) {
      console.error("There was an error in fetching the job job application.");
    }
  }
  return (
    <>
      <div className="job-description-card">
        <p>{jobDescription}</p>
        <p>{companyName}</p>
        <p>{companyLocation}</p>
        <p>{jobPosition}</p>
        <p>{requiredSkills}</p>
      </div>
    </>
  );
}

export default JobApplication;
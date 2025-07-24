import React, { useState, useEffect } from 'react';
import JobApplication from '../JobApplication/JobApplication';
import { DragDropContext } from "react-beautiful-dnd";
import './JobApplicationDashboard.css';
import DroppableColumn from '../DroppableColumn/DroppableColumn';

const JobApplicationDashboard = () => {
  const [applied, setApplied] = useState('');
  const [interviewing, setInterviewing] = useState('');
  const [rejected, setRejected] = useState('');
  const [offer, setOffered] = useState('');
  const [feedbackMessage, setFeedbackMessage] = useState('');
  const [jobApplications, setJobApplications] = useState([{}]);
  const getJobApplications = async () => {
    const url = "http://localhost:8080/job-applications";
    try {
      const response = await fetch(url);
      console.log("response: ", response);
      console.log("response data", response.formData);
      const data = await response.json();
      if (response.ok) {
        setJobApplications(data);
      } else {
        setFeedbackMessage("Your job applications could not be retrieved.");
      }
    } catch (error) {
      console.error("There was an error in fetching your job applications.");
    }
  }
  return (
    <DragDropContext>
      <h2>Job Applications Dashboard</h2>
      <div className="kanban-dashboard">
        <DroppableColumn>
          <h2>Applied</h2>
          {jobApplications.map((jobApplication, index) => 
          <JobApplication
            
          />
          )}
        </DroppableColumn>
        <DroppableColumn>
          <h2>Interviewing</h2>
        </DroppableColumn>
        <DroppableColumn>
          <h2>Rejected</h2>
        </DroppableColumn>
        <DroppableColumn>
          <h2>Offer</h2>
        </DroppableColumn>
      </div>
    </DragDropContext>
  );
}

export default JobApplicationDashboard;
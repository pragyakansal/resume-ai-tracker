import React, {useState, useEffect} from 'react';


const SkillsMatcherTool = () => {
  const [jobDescription, setJobDescription] = useState('');
  const [companyName, setCompanyName] = useState('');
  const [companyLocation, setCompanyLocation] = useState('');
  const [jobPosition, setJobPosition] = useState('');
  const [requiredSkills, setRequiredSkills] = useState('');
  const [feedbackMessage, setFeedbackMessage] = useState('');
  const uploadJobDescription = async (e) => {
    e.preventDefault();
    const url = 'http://localhost:8080/api/job-postings';
    try {
      const response = await fetch(url,  {
        method: "POST",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({companyName, companyLocation, jobPosition, jobDescription, requiredSkills}),
      });
      console.log("response: ", response);
      console.log("response data", response.formData);
      if (response.ok) {
        setFeedbackMessage('The job posting was uploaded successfully. Generating your skills match...');
        setCompanyName('');
        setCompanyLocation('');
        setJobPosition('');
        setJobDescription('');
        setRequiredSkills('');
      } else {
        setFeedbackMessage('The job posting could not be uploaded successfully. Please try again and make sure all fields entered are valid.');
      }
    } catch(error) {
        console.error('There was an error submitting the job posting, please try again.');
        setFeedbackMessage('An error occurred.');
    }  
  }
  return (
    <>
      <form>
        <input type="text" placeholder="Enter the company name here" value={companyName} onChange={(e) => setCompanyName(e.target.value)}/>
        <input type="text" placeholder="Enter the company location here" value={companyLocation} onChange={(e) => setCompanyLocation(e.target.value)}/>
        <input type="text" placeholder="Enter the job position here" value={jobPosition} onChange={(e) => setJobPosition(e.target.value)}/>
        <textarea 
          className="job-posting-submission-box"
          placeholder="Enter the job description here"
          type="text"
          value={jobDescription}
          onChange={(e) => setJobDescription(e.target.value)}
          required
        />
        <input type="text" placeholder="Enter the required skills here, if applicable, separated by commas" value={requiredSkills} onChange={(e) => setRequiredSkills(e.target.value)}/>
      </form>
    </>
  );
}

export default SkillsMatcherTool;
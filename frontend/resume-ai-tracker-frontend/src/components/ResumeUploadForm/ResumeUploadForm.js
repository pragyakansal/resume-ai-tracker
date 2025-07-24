import React, { useState, useEffect } from "react";

const ResumeUploadForm = () => {
  const [resume, setResume] = useState('');
  return (
    <>
    <form>
      <h2>Please enter your resume in the text box or upload it as a PDF or DOCX file. All other file types will be rejected.</h2>
      <textarea />
      <button></button>      
    </form>
    </>
  );
}
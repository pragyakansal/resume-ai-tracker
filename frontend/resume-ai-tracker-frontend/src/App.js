import logo from './logo.svg';
import './App.css';
import SkillsMatcherTool from './components/SkillsMatcherTool/SkillsMatcherTool';
import JobApplicationDashboard from './components/JobApplicationDashboard/JobApplicationDashboard';

function App() {
  return (
    <div className="App">
      <SkillsMatcherTool />
      <JobApplicationDashboard/>
    </div>
  );
}

export default App;

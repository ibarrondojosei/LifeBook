
import { LoginView } from './View/Login/LoginView';
import {BrowserRouter as  Routes, Route} from 'react-router-dom';
import ClinicalHistory from './View/ClinicalHistory/ClinicalHistory';
import CicloLectivo from './View/CicloLectivo/CicloLectivo';

function App() {
  return (
    <div className="App">
      <CicloLectivo/>
     
      {/* <RegisterView/> */}
  
    </div>
  );
}

export default App;

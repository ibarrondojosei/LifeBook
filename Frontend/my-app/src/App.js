
import { LoginView } from './View/Login/LoginView';
import {BrowserRouter as  Routes, Route} from 'react-router-dom';
import ClinicalHistory from './View/ClinicalHistory/ClinicalHistory';
import CicloLectivo from './View/CicloLectivo/CicloLectivo';
import Footer, {footer} from './components/Footer/Footer.jsx';
import NavBar from './components/NavBar/NavBar.jsx';

function App() {
  return (
    <div className="App">
      {/* <CicloLectivo/> */}
      <LoginView/>
      {/* <Footer/> */}
      {/* <NavBar/> */}
      {/* <ClinicalHistory/> */}
     
      {/* <RegisterView/> */}
      
      
  
    </div>
  );
}

export default App;

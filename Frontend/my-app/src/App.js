
import { LoginView } from "./View/Login/LoginView";
import { RegisterView } from "./View/Register/RegisterView";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ClinicalHistory from "./View/ClinicalHistory/ClinicalHistory";
import CicloLectivo from "./View/CicloLectivo/CicloLectivo";
import Footer from "./components/Footer/Footer.jsx";
import NavBar from "./components/NavBar/NavBar.jsx";
import Home from "./components/Home/Home";

import {Carousel} from "./Views/Carousel/Carousel.jsx"

function App() {
  return (
    <BrowserRouter className="App">
      <NavBar />
      
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<LoginView />} />
        <Route path="/register" element={<RegisterView />} />
        <Route path="/clinicalHistory" element={<ClinicalHistory />} />
        <Route path="/cicloLectivo" element={<CicloLectivo />} />
      </Routes>
    
      <Footer />
    </BrowserRouter>
  );
}

export default App;

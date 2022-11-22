import './App.css';

import Footer from './components/Footer/Footer';
import Home from './components/Home/Home'
import {BrowserRouter, Routes, Route} from 'react-router-dom';


function App() {
  return (
    <>
      <BrowserRouter>
      
      <Routes>
        <Route path='/' element={<Home/>}></Route>
      </Routes>
      
      <Footer/>
      </BrowserRouter>
      
      
      
    
    
    </>
    
  );
}

export default App;

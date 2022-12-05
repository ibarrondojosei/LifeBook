import Footer from './components/Footer/Footer';
import Home from './components/Home/Home'
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import LoadPhoto from './components/LoadPhoto/LoadPhoto';


function App() {
  return (
    <>
      <BrowserRouter>
      
      <Routes>
        <Route path='/' element={<Home/>}></Route>
        <Route path='/load' element={<LoadPhoto/>}></Route>
        
      </Routes>
      
      <Footer/>
      </BrowserRouter>
      
      
      
    
    
    
    </>
    
 
    
  );
}

export default App;

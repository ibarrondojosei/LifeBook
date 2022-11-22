import './App.css';

import Footer from './components/Footer/Footer';
import Home from './components/Home/Home'
import {BrowserRouter, Routes, Route} from 'react-router-dom';

import { RegisterView } from './View/Register/RegisterView';

function App() {
  return (
    <div className="App">
      <LoginView/>
  
    </div>
  );
}

export default App;

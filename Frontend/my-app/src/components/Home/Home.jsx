import React from 'react'
import '../Home/Home.css'
import NavBar from '../NavBar/NavBar'
import Destacados from './Destacados'
import { Link } from 'react-router-dom';
export default function Home() {
  return (
    <div className='container mx-auto '>
        <header>
          
            <div className='bgImg divHomeMobile '>
                <h1 >LifeBook</h1>
                <Link to='/crearpublicacion'>
                  <button className='btnHome'>Crear Publicación</button>
                </Link>
                <p className=''>Tu nueva forma de guardar recuerdos</p>
                <NavBar/>
            </div>
            <Destacados/>

        </header>
        
    </div>
  )
}

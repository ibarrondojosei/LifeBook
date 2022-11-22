import React from 'react'
import './Footer.css'
import { Link } from 'react-router-dom';

export default function Footer() {
  return (
    <>
      <footer className='footer'>
        <div className='sizeFooter'>
          <div className='footerOne listFooter'>
            <ul className='ulFooter list'>
              <Link to='/' className='liFooter'>
                <li >Inicio</li>
              </Link>
              <Link to='/destacados' className='liFooter'>
                <li >Destacados</li>
              </Link>
              <Link to='/salud' className='liFooter'>
                <li >Salud</li>
              </Link>
              <Link to='/educacion' className='liFooter'>
                <li >Educación</li>
              </Link>
              <Link to='/miperfil' className='liFooter'>
                <li >Perfil</li>
              </Link>
            </ul>
          </div>
          <div className='footerTwo footerMobile'>
            <p className='stylePFooterTwo'>Copyright 2022 © All reserved | LifeBook</p>
          </div>
        </div>
        

      </footer>

    </>
  )
}

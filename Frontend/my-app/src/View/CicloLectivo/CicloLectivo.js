import React from 'react'
import './ciclo.css';


const CicloLectivo = () => {
  return (
    <div>
       <nav className='barra'>
            <ul className='barraNavegacion'>
                <li className='opciones'>Home</li>
                <li className='opciones'> > </li>
                <li className='opciones'>Ciclo Lectivo</li>
            </ul>
       </nav>
       <div className='ciclo'>
            <h3>Ciclo lectivo</h3>
       </div>
       <div className="grid grid-cols-3 gap-3">
          <div className="content">
              <h3 className='años'>2020</h3>
              <h3 className='años'>2019</h3>
              <h3 className='años'>2018</h3>
              <h3 className='años'>2017</h3>
          </div>
          <div className="...">
              <img src="../CicloLectivo/Assets/video.png" alt='imagen de carga de video'/>
          </div>
          <div className="compas">
              <div>
                  <h3>Mis compañeros</h3>
                  <div>
                  <img src="../CicloLectivo/Assets/Rectangle 598.png" alt='imagen de carga de video'/>
                  <img src="../CicloLectivo/Assets/Rectangle 599.png" alt='imagen de carga de video'/>
                  <img src="../CicloLectivo/Assets/Rectangle 600.png" alt='imagen de carga de video'/>
                  <img src="../CicloLectivo/Assets/Rectangle 601.png" alt='imagen de carga de video'/>
                  <img src="../CicloLectivo/Assets/Rectangle 602.png" alt='imagen de carga de video'/>
                  <img src="../CicloLectivo/Assets/Rectangle 603.png" alt='imagen de carga de video'/>
                  </div>
              </div>
          </div>
        </div>
        <div className='cuadrito'>
          <h2>lorem ipsum</h2>
        </div>
       
    </div>
    
  )
}

export default CicloLectivo
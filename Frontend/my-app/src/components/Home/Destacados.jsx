import React from 'react'
import './Destacados.css'
import  {Modals}  from '../Modals/Modals.jsx';

export default function Destacados() {
    return (
        <>
            <h2 className='h2Home'>Momentos destacados</h2>
                <div className='one'>
                    <div className="cardMomentos" style={{ width: '18rem' }}>
                        <img src="/assets/Globos.svg" className="cardImg" alt="Carga un momentos destacado" />
                        <div className="cardBlack">
                            <Modals/>                    
                            <p >Agrega tu momento <br/> destacado</p>
                            
                        </div>
                        
                    </div>
                </div>


        </>
    )
}

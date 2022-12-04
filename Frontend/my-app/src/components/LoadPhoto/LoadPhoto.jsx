import React from 'react';
import NavBar from '../NavBar/NavBar';
import './LoadPhoto.css';
import {useState} from 'react'
export default function LoadPhoto() {
    const [ImagePrevious, setImagePrevious] = useState(null);
    const changeImage = (e)=>{
        const reader = new FileReader(); 
        reader.readAsDataURL(e.target.files[0])
        reader.onload= (e)=>{
            e.preventDefault();
            setImagePrevious(e.target.result);
        }
    }

    return (
        <div className='bgGral'>
            <NavBar />
            <h1 className='titleLoad'>Momento Destacado</h1>
            <div className='divMomentos'>
                <div className='divCarga'>
                    <h6>Cargar imagen</h6>
                    <h6 className='vistaPrevia'>Vista Previa</h6>
                </div>
                <div className='columnDivMoment'>
                    <div className='contornos widthTitle'>
                        <div className="inlineBlock">
                            <label className=''>Título</label>
                            <input type="text" className='borderInput' />
                        </div>
                        <div className="inlineBlock">
                            <label >Descripción</label>
                            <textarea className="borderInput" style={{ height: "100px" }} />
                        </div>
                    </div>  
                    <div className='contornosImg' >
                        <div className=' heightImg'>
                            <input type="file" accept='image/*' multiple onChange={e => changeImage(e)} />
                            <div className="borderInput">
                                <img src={ImagePrevious} alt="foto cargada" />
                            </div>
                            <p className='addImg'>Subir imagen</p>
                        </div>
                        <div className='padButton'>
                            <button className='marButtonCancel'>Cancelar</button>
                            <button className='marButtonPublic'>Publicar</button>
                        </div>
                    </div>
                </div>



            </div>


        </div>
    )
}

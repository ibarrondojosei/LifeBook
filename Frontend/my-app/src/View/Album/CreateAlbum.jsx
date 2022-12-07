import React , {useState} from 'react';
import Tags from "@yaireo/tagify/dist/react.tagify" // React-wrapper file
import "@yaireo/tagify/dist/tagify.css" // Tagify CSS
import "./CreateAlbum.css";

const srcImage = require("./cargar.png")
function CreateAlbum() {
    const [ImagePreviousAlbum, setImagePreviousAlbum] = useState(srcImage );
    const changeImage = (e)=>{
        const reader = new FileReader(); 
        reader.readAsDataURL(e.target.files[0])
        reader.onload= (e)=>{
            e.preventDefault();
            setImagePreviousAlbum(e.target.result);
        }
    }
    
const handleSubmit = (event) => {
    event.preventDefault();
    console.log("oi ")
}

    return (
    <div className='bgGralA'>
          
            <h1 className='titleAlbum'>Album</h1>
            <div className='divAlbum'>
                <div className='divCargaAlbum'>
                    <h6>Cargar imagen</h6>
                    <h6 className='vistaPreviaAlbum'>Vista Previa</h6>
                </div>
                <form onSubmit={handleSubmit}>
                <div className='columnDivAlbum'>
                    <div className=' widthTitleA'>
                        <div >
                            <label className='fontStyleAlbum'>Nombre:</label>
                            <input type="text" className='borderInputOneA'  />
                        </div>
                        <div >
                            <label className="fontStyleAlbum">Descripción</label>
                            <textarea className="borderInputThreeA" style={{ height: "100px" }} />
                        </div>
                        <div >
                            <label className='fontStyleAlbum'>Compartir com:</label>
                            <Tags/>
                            {/* <input type="text" className='borderInputOneA' /> */}
                        </div>
                    </div>  
                    <div className='contornosImgA' >
                        
                           <div className="borderInputTwoA heightImg grid place-items-center"> 
                                 
                                <img src={ImagePreviousAlbum} className="" alt="" />
                                <input  className='mx-auto' type="file" accept='image/*' multiple onChange={e => changeImage(e)} />
                            </div>
                            
                        
                        <div className='padButtonA'>
                            <button className='marButtonCancelA fontStyleAlbumButton'>Cancelar</button>
                            <button type="submit" className='marButtonPublicA fontStyleAlbumButton'>Publicar</button>
                        </div>
                    </div>
                </div>

                </form>

            </div>


        </div>
    )
  
}

export default CreateAlbum


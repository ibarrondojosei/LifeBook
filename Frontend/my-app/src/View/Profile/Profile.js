import React from "react";
import "./Profile.css";

export function Profile () {
	return (
	<div className="w-screen h-screen">
        <div><h1 className="relative flex  ml-24 w-2/4 mt-20  text-black-800 text-5xl font-bold">Mi Perfil</h1></div>
		<div className="absolute usuario ml-48 mt-20">
			<img src="https://res.cloudinary.com/djllzjsij/image/upload/v1670371062/User-60_pxn9lz.svg" alt="foto-usuario"></img>
			<textarea value="Sobre mí" className="texto ml-72 "></textarea>
		</div>

	<div className=" mt-72  items-end col-end-7 col-span-2 mr-12  h-60  ml-32 ">
	<form action=" grid grid-cols-2">


<div className="div1">
  
  <label for="name" className="inline-block w-20 mr-6 text-right font-bold text-gray-600">Nombre</label>
  <input type="text" id="nombre" name="nombre" placeholder="" className=" py-2 border-b-2 border-gray-400 
				text-gray-600 placeholder-gray-400
				outline-none" />
</div>

<div className="div2">
  <label className="inline-block w-20 mr-6 text-right font-bold text-gray-600">Apellido</label>
  <input type="text" id="apellido" name="apellido" placeholder="" 
		 class=" py-2 border-b-2 border-gray-400  
				text-gray-600
				placeholder-gray-400 outline-none" />
				</div>

<div className="div3">
<label for="name" className="inline-block w-20 mr-6 text-right font-bold text-gray-600">Dirección</label>
  <input type="text" id="dirección" name="dirección" placeholder="" className="py-2 border-b-2 border-gray-400  
				text-gray-600 placeholder-gray-400
				outline-none" />
</div>

<div className="div4">
  <label className="inline-block w-20 mr-6 text-right font-bold text-gray-600">Mail</label>
  <input type="text" id="mail" name="mail" placeholder="" 
		 class=" py-2 border-b-2 border-gray-400  
				text-gray-600
				placeholder-gray-400 outline-none" />  
</div>
<div class="text-right mt-32 mr-32">
        <button className="boton py-3 px-8 text-black font-bold">Actualizar perfil</button> 
      </div>

</form>
	</div>
		
	</div>
	);
};


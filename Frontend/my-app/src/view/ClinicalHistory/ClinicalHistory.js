import React from "react";

export default function ClinicalHistory() {
  return (
    <div className="w-screen h-screen">
        <h1 className=" top-20 left-20 text-black-800 text-5xl font-bold">
          Historia Clínica
        </h1>
      <div class=" pointer-events-auto space-y-10 top-40 left-20 text-black-400 text-2xl">
        <h3>2021</h3>
        <h3>2020</h3>

        <h3>2019</h3>

        <h3>2018</h3>

        <h3>2017</h3>

        <h3>2016</h3>

        <h3>2015</h3>
      </div>
      <div className=" justify-items-center top-25 left-60 bottom-20">
        <h6 className="text-sm font-normal">DATOS IMPORTANTES</h6>
		<button id="dropdownDefault" data-dropdown-toggle="dropdown" className="text-black bg-white focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2.5 text-center inline-flex items-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" type="button">Seleccionar <svg className="ml-2 w-4 h-4" aria-hidden="true" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7"></path></svg></button>

<div id="dropdown" className="hidden z-10 w-44 bg-white rounded divide-y divide-gray-100 shadow dark:bg-gray-700">
    <ul class="py-1 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="dropdownDefault">
      <li>
        <a href="birth" className="block py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Nací el día</a>
      </li>
      <li>
        <a href="babyWeight" className="block py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Peso</a>
      </li>
      <li>
        <a href="babySize" className="block py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Talle</a>
      </li>
      <li>
        <a href="bloodType" className="block py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Grupo y factor</a>
      </li>
	  <li>
	  <a href="pediatricianName" className="block py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Mi pediatra</a>
	  </li>
    </ul>
</div>
      </div>
	  
    </div>
  );
}

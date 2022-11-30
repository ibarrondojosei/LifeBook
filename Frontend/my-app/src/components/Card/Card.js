import React from 'react';
import { IconContext } from "react-icons";
import {BiShareAlt } from "react-icons/bi";

function Card({info, index}) {
  const src = require('../../components/assets' + info.image);
  const indexClass = `index-${index}`;
  return (
    <div className={`flex-shrink-0 card h-80		 ${indexClass}`} style={{maxWidth: '14rem'}}>
      
      <div className=''>
        <div className="  pt-1.5	px-1.5 pb-4 rounded-md overflow-hidden shadow-lg  flex md:flex-col">
       
          <img className="w-5/12	 md:w-full h-48 object-cover rounded mb-3" src={src} alt={info.title}/>
         <div> 
              <div className="">
                <div className="font-bold text-xl mb-2 text-center">{info.title}</div>
                  <p className="text-gray-700 pb-9 text-xs text-left">
                    {info.text}
                  </p>
              </div>
            {/*Button*/}
            <div className='flex flex-row justify-around'>
              <button className='font-bold invisible  md:visible'>Ver</button>
              <button className="py-2 px-4 text-base flex flex-row md:bg-celeste rounded-full items-center">
              <p className='font-semibold  pr-2  invisible md:visible	'>Compartir</p>
              <span >
                <IconContext.Provider  value={{  className: "1.4rem" }}>
                  <BiShareAlt />
                </IconContext.Provider>
              </span>
              </button>
          </div>
          </div>
</div>
      </div>
    </div>
  )
}

export {Card}

import React from 'react';
import { IconContext } from "react-icons";
import {BiShareAlt } from "react-icons/bi";

function Card({info, index}) {
  const src = require('../../components/assets' + info.image);
  const indexClass = `index-${index}`;
  return (
    <div className={`flex-shrink-0 card ${indexClass}`} style={{maxWidth: '16rem'}}>
      
      <div>
        <div className="  pt-1.5	px-1.5 pb-4 rounded-md overflow-hidden shadow-lg">
       
          <img className="w-full h-48 object-cover rounded mb-3" src={src} alt={info.title}/>
          
          <div className="">
            <div className="font-bold text-xl mb-2 text-center">{info.title}</div>
              <p className="text-gray-700 pb-9 text-xs text-left">
                {info.text}
              </p>
             </div>
        {/*Button*/}
        <div className='flex flex-row justify-around'>
          <button className='font-bold'>Ver</button>
          <button className="py-2 px-4 text-base flex flex-row bg-celeste rounded-full items-center">
          <p className='font-semibold  pr-2	'>Compartir</p>
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
  )
}

export {Card}

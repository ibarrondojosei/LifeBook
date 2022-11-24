import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCircleArrowRight, faCircleArrowLeft }  from '@fortawesome/free-solid-svg-icons'


const Arrow = ({movPrevFn, movNextFn}) => {
    return (

        
            <>
            <button onClick={movPrevFn}
            //disabled={isDisabled('prev')}
            >
            <FontAwesomeIcon icon={faCircleArrowLeft} className=" absolute left-0 bg-white rounded-full  " size='3x'/>
            </button>
            <button 
            
            onClick={movNextFn}
            //disabled={isDisabled('next')}
           
>
              <FontAwesomeIcon icon={faCircleArrowRight} className=" absolute right-0  bg-white rounded-full "  size="3x"/>
            </button>
            </>
    
    )
}
export default Arrow

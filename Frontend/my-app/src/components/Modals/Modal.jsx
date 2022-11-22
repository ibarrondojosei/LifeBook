
import './Modal.css'

import React from 'react'

export default function Modal({children, isOpen, closeModal}) {
    const handleModalContainerClick = (e) => e.stopPropagation();
  return (
    <article className={`modal ${isOpen && "isOpen"}`} onClick={closeModal}>
        <div className="modalContainer" onClick={handleModalContainerClick}>
            <button className='modalClose' onClick={closeModal}>X</button>
            {children}
        </div>

    </article>
  )
}


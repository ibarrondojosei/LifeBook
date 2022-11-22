import React from 'react'
import { useModal } from '../../hooks/useModal'
import Modal from '../Modals/Modal'


export const Modals = () => {
    const [isOpenModal, openModal, closeModal] = useModal(false);
  return (
    <div>
        <button className='buttonAgregar' onClick={openModal} ></button>
        <Modal isOpen={isOpenModal} closeModal={closeModal}>
            <p className='pModals' style={{ color: 'black'}}>¿Desea crear un Momento Destacado?</p>
            <button className='btnCli'>Historia clinica</button>
            <button className='btnEdu'>Ciclo lectivo</button>
        </Modal>
            
        
        
    </div>
  )
}

import React, {useState} from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../hooks/Context/AuthContext";
import axios from "axios";
import "./RegisterView.css"

export const RegisterView = () => {
    const [user, setUser] = useState({
      nombre:'',
      apellido:'',
      userName:'',
      email: '',
      password: '',
  })

  /*const {signUp} = useAuth();
  const navigate = useNavigate();
  const [error, setError] = useState()*/



  const handleChange = ({target: {name, value}}) => {
      setUser({...user,[name]: value})
  };

  const handleSubmit = async e => {
      e.preventDefault()
      axios.post('https://s5-03-java-react-production.up.railway.app/auth/register', {
        email: user.email,
        password: user.password,
        nombre: user.nombre,
        apellido: user.apellido,
        userName: user.userName
      })
      .then(res => localStorage.setItem('token', res.data.token))

  }

  return (
    <div className="register">
      <div className="  w-full max-w-xs    ">
        <form className=" bg-white shadow-md rounded-lg px-12 pt-6 pb-8 w-96 float-right mx-56 mt-20" onSubmit={handleSubmit}>
          <h1 className="text-gray-800 text-center font-bold p-4 pb-8">
           Registrar  
          </h1>
          <div className="mb-4">
            <label
              className="block text-gray-700 text-sm font-bold mb-2"
              for="name"
            >
              Nombre
            </label>
            <input
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              id="name"
              type="text"
              placeholder="Nombre"
              required
              
            />
          </div>

          <div className="mb-4">
            <label
              className="block text-gray-700 text-sm font-bold mb-2"
              for="surname"
            >
              Apellido
            </label>
            <input
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              id="surname"
              type="text"
              placeholder="Apellido"
              required
              
            />
          </div>
          <div className="mb-6">
            <label
              className="block text-gray-700 text-sm font-bold mb-2"
              for="username"
            >
              Nombre de usuario
            </label>
            <input
              className="shadow appearance-none border b rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="username"
              type="text"
              placeholder="Nombre de usuario"
              required
            />
          </div>
          <div className="mb-6">
            <label
              className="block text-gray-700 text-sm font-bold mb-2"
              for="mail"
            >
              Mail
            </label>
            <input
              className="shadow appearance-none border b rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="mail"
              type="text"
              placeholder="Mail"
              required
              onChange={handleChange}
            />
          </div>
          <div className="mb-6">
            <label
              className="block text-gray-700 text-sm font-bold mb-2"
              for="password"
            >
              Contraseña
            </label>
            <input
              className="shadow appearance-none border b rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="password"
              type="password"
              placeholder="Contraseña"
              onChange={handleChange} 
              required
            />
          </div>

          <div>
            <button
              className="  block bg-teal-400 hover:bg-teal-600 font-bold text-black uppercase text-lg mx-auto p-2 rounded mt-4"
              type="button"
            >
              Registrar
            </button>
          </div>
        </form>
      </div>
      <img
        className="ml-16 pt-32"
        src="https://res.cloudinary.com/dytpump6i/image/upload/v1668705729/LifeBook2_2_1_kdgf1m.png"
        alt=""
      />
    </div>
  );
};

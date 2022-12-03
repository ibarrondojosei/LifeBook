import React, {useState} from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../hooks/Context/AuthContext";
import "./RegisterView.css"

export const RegisterView = () => {
  const [user, setUser] = useState({
    email: '',
    password: '',
})

const {signUp} = useAuth();
const navigate = useNavigate();
const [error, setError] = useState()



const handleChange = ({target: {name, value}}) => {
    setUser({...user,[name]: value})
};

const handleSubmit = async e => {
    e.preventDefault()
    try{
        await signUp(user.email, user.password)
        navigate('/')
    }catch(error){
        setError(error.message)
    }

}

  return (
    <div className="register">
      <div className="  w-full max-w-xs float-right mr-36 mt-12   ">
        <form className=" bg-white shadow-md rounded-lg px-12 pt-6 pb-8 w-96  " onSubmit={handleSubmit}>
          <h1 className="text-gray-800 text-center font-bold p-4 pb-8">
           Registrar
          </h1>
          <div className="mb-4">
            <label
              className="block text-gray-700 text-sm font-bold mb-2"
              for="username"
            >
              Nombre
            </label>
            <input
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              id="username"
              type="text"
              placeholder="Nombre"
              
            />
          </div>

          <div className="mb-4">
            <label
              className="block text-gray-700 text-sm font-bold mb-2"
              for="username"
            >
              Apellido
            </label>
            <input
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              id="username"
              type="text"
              placeholder="Apellido"
              
            />
          </div>
          <div className="mb-6">
            <label
              className="block text-gray-700 text-sm font-bold mb-2"
              for="password"
            >
              Nombre de usuario
            </label>
            <input
              className="shadow appearance-none border b rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="password"
              type="password"
              placeholder="Nombre de usuario"
            />
          </div>
          <div className="mb-6">
            <label
              className="block text-gray-700 text-sm font-bold mb-2"
              for="password"
            >
              Mail
            </label>
            <input
              className="shadow appearance-none border b rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="password"
              type="password"
              placeholder="Mail"
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

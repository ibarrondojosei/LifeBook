import React from "react";
import { useState } from "react";
import "./LoginView.css";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../hooks/Context/AuthContext";

// import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
// import { faUser, faKey } from "@fortawesome/free-solid-svg-icons";

export const LoginView = () => {

    const [user, setUser] = useState({
      username:'',
      password: '',
    });

    const {login} = useAuth();
    const navigate = useNavigate();
    const [error, setError] = useState()

    const handleChange = ({target: {name, value}}) => {
      setUser({...user,[name]: value})
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    try{
      await login(user.username, user.password)
      navigate('/registro');
    }catch(error){
      
      setError(error.message)
      
    }
  };

  return (
    <div className="login">
      <div className="  w-full max-w-xs   ">
        <form className=" bg-white shadow-md rounded-lg px-12 pt-6 pb-8 w-96 float-right mx-56 mt-20  " onSubmit={handleSubmit}>
          <h1 className="text-gray-800 text-center font-bold p-4 pb-8">
            Inicio de sesion
          </h1>
          <div className="mb-8">
            <div class="flex justify-center items-center text-sm ">
              <label className="mr-2  ">¿Eres nuevo usuario?</label>
              <Link to="/register"><a className=" cursor-pointer text-blue-500 transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
                Crear una cuenta
              </a></Link>
            </div>
          </div>

          <div className="mb-4">
            <div className=" absolute ml-4 m-2">
              {/* <FontAwesomeIcon icon={  faUser} /> */}
            </div>

            <input
              className=" shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              id="username"
              type="text"
              placeholder="Email"
              onChange={handleChange}
            />
          </div>
          <div className="mb-6">
         <div className="absolute ml-4 m-2">
         {/* <FontAwesomeIcon icon={faKey} /> */}
         </div>
            <input
              className="shadow appearance-none border b rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="password"
              type="password"
              placeholder="Password"
              onchange={handleChange}
            />
          </div>
          <div className="flex items-center justify-between">
            <div className="flex items-center">
              <input
                id="recuerdame"
                name="recuerdame"
                type="checkbox"
                class="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded"
              />
              <label for="remember_me" class="ml-2 block text-sm text-gray-900">
                Recuerdame
              </label>
            </div>

            <div className="text-sm">
              <a className=" cursor-pointer font-medium text-indigo-600 hover:text-indigo-500">
                Olvido su contraseña?
              </a>
            </div>
          </div>
          <div>
            <button
              className="  block bg-teal-400 hover:bg-teal-600 font-bold text-black uppercase text-lg mx-auto p-2 rounded mt-4"
              type="button"
            >
              Ingresar
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

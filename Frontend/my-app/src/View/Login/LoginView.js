import React from "react";
import "./LoginView.css";

export const LoginView = () => {
  return (
    <div className="login">
      <div className="  w-full max-w-xs float-right m-36  ">
        <form className=" bg-white shadow-md rounded-lg px-12 pt-6 pb-8 w-96  ">
          <h1 className="text-gray-800 text-center font-bold p-4 pb-8">
            Inicio de sesion
          </h1>
          <div className="mb-8">
            <div class="flex justify-center items-center">
              <label className="mr-">¿Eres nuevo usuario?</label>
              <a
              
                className=" text-blue-500 transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105"
              >
                Crear una cuenta
              </a>
            </div>
          </div>

          <div className="mb-4">
            <label
              className="block text-gray-700 text-sm font-bold mb-2"
              for="username"
            >
              Username
            </label>
            <input
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              id="username"
              type="text"
              placeholder="Username"
            />
          </div>
          <div className="mb-6">
            <label
              className="block text-gray-700 text-sm font-bold mb-2"
              for="password"
            >
              Password
            </label>
            <input
              className="shadow appearance-none border b rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="password"
              type="password"
              placeholder="password"
            />
          </div>
          <div className="flex items-center justify-between">
					<div className="flex items-center">
						<input id="recuerdame" name="recuerdame" type="checkbox" class="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded"/>
						<label for="remember_me" class="ml-2 block text-sm text-gray-900">
							Recuerdame
						</label>
					</div>

					<div className="text-sm">
						<a  className="font-medium text-indigo-600 hover:text-indigo-500">
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
    </div>
  );
};

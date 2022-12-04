import React from "react";
import "./ClinicalHistory.css";

export default function ClinicalHistory() {
  return (
    <div className="w-screen h-screen">
      <h1 className="relative top-20 left-20 text-black-800 text-5xl font-bold">
        Historia Clínica
      </h1>
      <div className="relative pointer-events-auto space-y-10 top-20 left-20 text-black-400 text-2xl">
        <h3 className="años">2021</h3>
        <h3 className="años">2020</h3>
        <h3 className="años">2019</h3>
        <h3 className="años">2018</h3>
        <h3 className="años">2017</h3>
        <h3 className="años">2016</h3>
        <h3 className="años">2015</h3>
      </div>
      <div className="video">
        <img
          src="https://res.cloudinary.com/djllzjsij/image/upload/v1670184929/Lifebook%20files/Group_29_p7afzu.png"
          alt="video"
        ></img>
      </div>
	  <div className="fotos grid grid-cols-2 gap-2">
		<img src="https://res.cloudinary.com/djllzjsij/image/upload/v1670184928/Lifebook%20files/Rectangle_597_g5wsp1.png" alt="photo1"></img>
		<img src="https://res.cloudinary.com/djllzjsij/image/upload/v1670184929/Lifebook%20files/Rectangle_598_v6evog.png" alt="photo2"></img>
		<img src="https://res.cloudinary.com/djllzjsij/image/upload/v1670184928/Lifebook%20files/Rectangle_599_hb61hn.png" alt="photo3"></img>
		<img src="https://res.cloudinary.com/djllzjsij/image/upload/v1670184928/Lifebook%20files/Rectangle_600_eyawan.png" alt="photo4"></img>
		<img src="https://res.cloudinary.com/djllzjsij/image/upload/v1670184929/Lifebook%20files/Rectangle_601_sqtz21.png" alt="photo5"></img>
		<img src="https://res.cloudinary.com/djllzjsij/image/upload/v1670184929/Lifebook%20files/Rectangle_602_g7akag.png" alt="photo6"></img>
	  </div>
      
    </div>
  );
}

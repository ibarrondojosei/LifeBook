import React, {useState,useEffect} from 'react'
import "./Carousel.css"
import Arrow from "../../components/Arrows/Arrows.js"
import {Card} from "../../components/Card/Card.js"
import data from "../../components/Card/data.json"


const Carousel = (props) =>{
   // Carousel****

  const [currentIndex, setCurrentIndex] = useState(0);
  const [showedCardIndexes, setShowedCardIndexes] = useState([...data.defaultList].splice(0, 4).map(card => card.id));
  
  const movePrev = () => {
    if (currentIndex > 0) {
      setCurrentIndex((prevState) => {
        let newIndex = prevState - 1;
        setShowedCardIndexes(data.defaultList.slice(newIndex, newIndex + 4).map(card => card.id));
        return newIndex;
      });
    }
  };

  const moveNext = () => {
    if (currentIndex + 4 < defaultList.length) {
      setCurrentIndex((prevState) => {
        let newIndex = prevState + 1;
        setShowedCardIndexes(data.defaultList.slice(newIndex, newIndex + 4).map(card => card.id)); // []
        return newIndex;
      });
    } else {
      setCurrentIndex(0)
    }
  };

  useEffect(() => {
    const anime = setInterval(() => moveNext(), 2000);
    return () => clearInterval(anime);
  }, [currentIndex]);

  const [defaultList, setDefaultList] = useState(data.defaultList);
  
  const mycallback = (element, index) => {
    return <Card key={element.id} info={element} index={index}></Card>
  };

  return (
    <div className=" relative max-w-screen-custom bg-none mx-auto h-screen flex flex-col justify-center bg-no-repeat" /*style={{backgroundImage: `url("${require('../../components/assets/images/bg/bg-image.png')}")`}}*/>
        {/*gradient*/}
        <div className='gradient-celeste absolute top-0 left-0 w-36 h-96 invisible md:visible'></div>
        <div className='absolute w-80	h-72	bg-no-repeat top-0 right-0 invisible md:visible ' style={{backgroundImage: `url("${require('../../components/assets/images/bg/stars.png')}")`}}></div>
        
        <h2 className= "text-5xl text-left ml-20 mb-11 mt-8 font-bold">Mis álbumes</h2>
        <div
        className='bg-container-card pb-20 px-14 overflow-hidden rounded-lg
        mx-auto bg-gradient-to-r from-light-celeste via-transparent	to-light-celeste'>
          <Arrow movPrevFn={movePrev} movNextFn={moveNext}></Arrow>
            <div className='container-card mx-auto flex justify-center items-center relative  z-0' >
                <div className="px-5 flex flex-row gap-2">
                { defaultList.filter(card => showedCardIndexes.includes(card.id)).map(mycallback)}
                </div>
            </div>
        </div>
    </div>
  )
}

export {Carousel}
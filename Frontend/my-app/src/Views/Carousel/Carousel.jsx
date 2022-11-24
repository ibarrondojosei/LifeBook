import React, {useState,useRef,useEffect} from 'react'
import "./Carousel.css"
import Arrow from "../../components/Arrows/Arrows.js"
import {Card} from "../../components/Card/Card.js"
import data from "../../components/Card/data.json"


const Carousel = (props) =>{
   // Carousel****
   const maxScrollWidth = useRef(0);
  const [currentIndex, setCurrentIndex] = useState(0);
  const carousel = useRef(null); //div which houses the carousel contents,when and how to scroll and get values relating to the carousel's width.
  const [showedCardIndexes, setShowedCardIndexes] = useState([...data.defaultList].splice(0, 4).map(card => card.id));
  // console.log('init', currentIndex)
  // console.log(showedCardIndexes)
  
  const movePrev = () => {
    if (currentIndex > 0) {
      setCurrentIndex((prevState) => {
        // console.log('MovePrev', currentIndex)
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
        // console.log('MoveNext', currentIndex)
        setShowedCardIndexes(data.defaultList.slice(newIndex, newIndex + 4).map(card => card.id)); // []
        return newIndex;
      });
    }
  };

  // useEffect(() => {
  //   maxScrollWidth.current = carousel.current
  //     ? carousel.current.scrollWidth - carousel.current.offsetWidth
  //     : 0;
  // }, []);
  // useEffect(() => {
  //   // if (carousel !== null && carousel.current !== null) {
  //     carousel.current.scrollLeft = carousel.current.offsetWidth * currentIndex;
  //   // }
  // }, [currentIndex]);

   const [defaultList, setDefaultList] = useState(data.defaultList);
    const mycallback = (element, index) => {
        return <Card key={element.id} info={element} index={index}></Card>
    };

    
    return (
        
            <div className="max-w-screen-custom mx-auto h-screen flex flex-col justify-center bg-no-repeat" style={{backgroundImage: `url("${require('../../components/assets/images/bg/bg-image.png')}")`}}>
                
                <h2 className= "text-5xl text-left ml-32 font-bold">Mis álbumes</h2>
                <div 
                ref={carousel}
                className='bg-container-card w-4/5	 overflow-hidden pb-9 pt-9 max-w-screen-xl	rounded-lg
                mx-auto mt-9  bg-gradient-to-r from-light-celeste via-transparent	to-light-celeste'>
                    <Arrow movPrevFn={movePrev} movNextFn={moveNext}></Arrow>
                    <div className='container-card mx-auto flex justify-center items-center relative  z-0' >
                        <div 
                         className="px-5 flex flex-row gap-2 		 "
                        >
                        { defaultList.filter(card => showedCardIndexes.includes(card.id)).map(mycallback)
                
                            }
             
                        </div>
                    </div>
                    
                </div>
            </div>
   

  )
}

export {Carousel}

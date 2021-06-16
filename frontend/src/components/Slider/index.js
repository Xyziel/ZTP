import React from "react";
import {Carousel, Container, Row} from "react-bootstrap";
import "../../styles/css/slider.css"
import {AnimalCard} from "../AnimalCard";

function mapAnimalToCaruselItems(animals, interestedIds, likes, addInterestFunc, deleteInterestFunc, isLoggedIn) {
    var items = [];
    for(let i = 0; i < animals.length; i+=2) {
        items.push(<Carousel.Item key={animals[i].idAnimal}>
            <Row className="justify-content-around">
              <AnimalCard key={animals[i].idAnimal}
                          animal={animals[i]}
                          isInterested={interestedIds.includes(animals[i].idAnimal)}
                          persons={likes[animals[i].idAnimal]}
                          addInterestFunc={addInterestFunc}
                          deleteInterestFunc={deleteInterestFunc}
                          isLoggedIn={isLoggedIn}/>
                {i < animals.length - 1? <AnimalCard key={animals[i+1].idAnimal}
                                                      animal={animals[i+1]}
                                                      isInterested={interestedIds.includes(animals[i+1].idAnimal)}
                                                      persons={likes[animals[i+1].idAnimal]}
                                                      addInterestFunc={addInterestFunc}
                                                      deleteInterestFunc={deleteInterestFunc}
                                                      isLoggedIn={isLoggedIn}/> : null}
            </Row>
        </Carousel.Item>)

    }
    return <Carousel interval={4000} pause={false}>
            {items.map((item) => item)}
            </Carousel>
}

export function Slider({animals, interestedIds, likes, addInterestFunc, deleteInterestFunc, isLoggedIn}) {
    return (
        <Container className="sliderContainer" >
            <p className="sliderLabel">Ostatnio trafili do nas</p>
        {animals.length > 0 ? mapAnimalToCaruselItems(animals, interestedIds, likes, addInterestFunc, deleteInterestFunc, isLoggedIn) : null}

        </Container>
    );

}
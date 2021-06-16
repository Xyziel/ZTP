import React from "react";
import {Image, Button} from "react-bootstrap";
import "../../styles/css/animalCard.css"
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faPaw} from "@fortawesome/free-solid-svg-icons";
import {InterestedButton} from "../InterestButton";


export function AnimalCard({animal, persons = 0, isInterested, addInterestFunc, deleteInterestFunc, isLoggedIn, role}) {
    const interestCounter = isInterested ? "interestCounter blueish" : "interestCounter greenish";
    return (
        <div className="animalCardContainer p-0">
            <div className={interestCounter} id="animalCardCounter">{persons}<FontAwesomeIcon icon={faPaw}/>
                <span className="tooltiptext">{persons} - osoby zainteresowane tym zwierzakiem</span>
            </div>
            <div className="animalInfo">
                <Image src={"/static/images/" + animal.name + ".jpg"} className="animalImage" alt="Image available"/>
                <div className="info">
                    <p className="animalName">{animal.name}</p>
                    <p className="description">{animal.description}</p>

                    <InterestedButton isInterested={isInterested}
                                      addInterestFunc={addInterestFunc}
                                      deleteInterestFunc={deleteInterestFunc}
                                      animalId={animal.idAnimal}
                                      isLoggedIn={isLoggedIn}
                                      role={role}/>


                </div>
            </div>
            <Button variant="secondary" className="moreInfoButton" href={'/animal/' + animal.idAnimal}>Więcej</Button>
        </div>
    );
}


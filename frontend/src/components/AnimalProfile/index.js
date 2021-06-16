import React from "react";
import {Button, Image} from "react-bootstrap";
import "../../styles/css/animalProfile.css"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPaw} from "@fortawesome/free-solid-svg-icons";
import {InterestedButton} from "../InterestButton";

function showAlert(deleteAnimalFunc, id) {
    var deleteAnimal = window.confirm("Jesteś pewny, że chcesz usunąć to zwierzę?")
    if(deleteAnimal) {
        deleteAnimalFunc(id);
    }
}

export function AnimalProfile({animal, persons, isInterested,
                                  addInterestFunc, deleteInterestFunc, isLoggedIn,
                                  role, deleteAnimalFunc}) {
    const interestCounter = isInterested ? "interestCounter blueish m-auto lf-auto" : "interestCounter greenish m-auto lf-auto";

    return (
      <div className="animalProfileContainer" data-testid="profileContainer">
          <p className="animalProfileName">{animal.name}</p>

          <div className="imageData">
              <Image className="animalImageProfile" src={"/static/images/"+animal.name+".jpg"}/>
              <div className="data">
                  <p>Data przybycia: {animal.arrivalDate !== null ? animal.arrivalDate.slice(0, 10) : null}</p>
                  <p>Rasa: {animal.breed.name}</p>
                  <p>Rozmiar: {animal.size.name}</p>
                  <br/>
                  <div className={interestCounter}>{persons}<FontAwesomeIcon icon={faPaw} />
                      <span className="tooltiptext">{persons} - osoby zainteresowane tym zwierzakiem</span>
                  </div>
              </div>
          </div>
          <p className="descriptionProfile">{animal.description}</p>
          <InterestedButton isInterested={isInterested}
                            addInterestFunc={addInterestFunc}
                            deleteInterestFunc={deleteInterestFunc}
                            animalId={animal.idAnimal}
                            isLoggedIn={isLoggedIn}
                            role={role}/>
          {
              role === "EMPLOYEE" ?
                  <div>
                      <Button variant="danger" data-testid="employeeButton" onClick={() => showAlert(deleteAnimalFunc, animal.idAnimal)}>Usuń</Button>
                  </div>
                  : null
          }
      </div>

    );
}
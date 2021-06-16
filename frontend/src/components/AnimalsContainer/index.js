import React from "react";
import {AnimalCard} from "../AnimalCard";
import "../../styles/css/animalsContainer.css"

export function AnimalsContainer({animals, interestedIds, likes, addInterestFunc, deleteInterestFunc, isLoggedIn, role}) {
    return (
        <div>
            <p className="animalContainerLabel">Nasze zwierzaki:</p>
            <div className="animalsCards">
                {
                    animals.map((animal) =>
                        <AnimalCard key={animal.idAnimal}
                                    animal={animal}
                                    isInterested={interestedIds.includes(animal.idAnimal)}
                                    persons={likes[animal.idAnimal]}
                                    addInterestFunc={addInterestFunc}
                                    deleteInterestFunc={deleteInterestFunc}
                                    isLoggedIn={isLoggedIn}
                                    role={role}/>
                    )
                }
            </div>
        </div>
    );
}
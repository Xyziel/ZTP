import React from "react";

export function InterestedButton({isInterested, addInterestFunc, deleteInterestFunc, animalId, isLoggedIn, role}) {

    if(role === "EMPLOYEE") {
        return (
            <button className="interestButton" data-testid="button">
                Jesteś zainteresowany?<br/>
                Daj znać innym! Kliknij!</button>
        );
    }
    else {
        if (isInterested) {
            return (
                <button className="interestButton reddish" data-testid="button" onClick={() => deleteInterestFunc(animalId)}>
                    Nie jestem już<br/>
                    zainteresowany</button>
            );
        } else {
            if (isLoggedIn) {
                return (
                    <button className="interestButton" data-testid="button" onClick={() => addInterestFunc(animalId)}>
                        Jesteś zainteresowany?<br/>
                        Daj znać innym! Kliknij!</button>
                );
            } else {
                return (
                    <button className="interestButton" data-testid="button" onClick={() => {
                        alert("Musisz sie najpierw zalogować!");
                        window.location.replace("/login");
                    }}
                    >Jesteś zainteresowany?<br/>Daj znać innym! Kliknij!
                    </button>
                );
            }
        }
    }

}
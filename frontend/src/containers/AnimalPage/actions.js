import {ActionTypes} from "./constants";
import axios from 'axios';
import {getUserInterests} from "../HomePage/actions";

export function setAnimal(animal) {
    return {
        type: ActionTypes.SET_ANIMAL,
        animal
    }
}

export function setAnimalLikes(likes) {
    return {
        type: ActionTypes.SET_ANIMAL_LIKES,
        likes
    }
}

export const getAnimalById = (id) => (dispatch) => {
    axios.get("http://localhost:8080/animal/" + id)
        .then((response) => dispatch(setAnimal(response.data)))
        .catch((e) => {
            alert("Nie udało się załadować profilu zwierzaka");
            window.location.replace("/");
        });
};

export const getAnimalLikes = (id) => (dispatch) => {
    if (id != null) {
        axios.get("http://localhost:8080/interest/getAllLikesByAnimalId/"+id)
            .then((response) => {
                dispatch(setAnimalLikes(response.data))
            })
            .catch((e) => alert("Nie można załadować liczby zainteresowanych!"));
    }
}

export const addLikeProfile = (idAnimal) => (dispatch) => {
    axios.post("http://localhost:8080/interest", idAnimal)
        .then((response) => {
            dispatch(getAnimalLikes(idAnimal))
            dispatch(getUserInterests())
        })
        .catch((e) => {
            if(e.response.status === 409) {
                alert("Już lubisz to zwierzę");
            } else {
                alert("Wystąpił nieoczekiwany błąd!")
            }
        })
}

export const deleteLikeProfile = (id) => (dispatch) => {
    axios.delete("http://localhost:8080/interest/"+id)
        .then((respnse) => {
            dispatch(getAnimalLikes(id))
            dispatch(getUserInterests())
        })
        .catch((e) => {
            if(e.response.status === 404) {
                alert("Musisz najpierw być zainteresowany zwierzęciem!")
            } else {
                alert("Wystąpił nieoczekiwany błąd!")
            }
        })
}

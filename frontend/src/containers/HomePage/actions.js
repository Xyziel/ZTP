import {ActionTypes} from "./constants";
import axios from 'axios';
import {setLogin, setRole} from "../LoginPage/actions";

export function setAnimals(animals) {
    return {
        type: ActionTypes.SET_ANIMALS,
        animals
    }
}

export function setRecentAnimals(recentAnimals) {
    return {
        type: ActionTypes.SET_RECENT_ANIMALS,
        recentAnimals
    }
}

export function setLikesAnimal(id, likesById) {
    return {
        type: ActionTypes.SET_LIKES,
        id, likesById
    }
}

export function setUserInterests(ids) {
    return {
        type: ActionTypes.SET_USER_INTERESTS,
        ids
    }
}

export const getAnimals = (isEmployee, isLoggedIn) => (dispatch) => {
    axios.get("http://localhost:8080/animal")
        .then((response) => {
            dispatch(setAnimals(response.data));
            dispatch(getAnimalsLikes(response.data));
            if(!isEmployee && isLoggedIn) {
                dispatch(getUserInterests());
            }
        })
            .catch((e) => {
                alert("Nie możemy w tej chwili pokazać Ci naszych zwierząt. Przepraszamy.")
            });

};

export const getRecentAnimals = () => dispatch => {
    axios.get("http://localhost:8080/animal/getLastSix")
        .then((response) => dispatch(setRecentAnimals(response.data)))
        .catch((e => dispatch(setRecentAnimals([]))))
}

const getAnimalsLikes = (animals) => (dispatch) => {
    for(let i = 0; i < animals.length; i++) {
        var id = animals[i].idAnimal
        axios.get("http://localhost:8080/interest/getAllLikesByAnimalId/"+id)
            .then((response) => {
                dispatch(setLikesAnimal(animals[i].idAnimal, response.data))
            })
            .catch((e) => alert("Błąd przy ładowaniu zainteresowań zwierząt."));
    }
}

export const getUserInterests = () => (dispatch) => {
    axios.get("http://localhost:8080/interest/getAnimalsIdThatUserLikes")
        .then((response) => dispatch(setUserInterests(response.data)))
        .catch((e) => {
            alert("Wystąpił błąd! Nie możemy w tej chwili pokazać Ci zwierzaków, którymi jesteś zainteresowany")
        })
}

export const addLike = (idAnimal) => (dispatch) => {
    axios.post("http://localhost:8080/interest", idAnimal)
        .then(dispatch(getAnimals(false, true)))
        .catch((e) => {
            if(e.response.status === 409) {
                alert("Już lubisz to zwierzę");
            } else {
                alert("Wystąpił nieoczekiwany błąd!")
            }
        })
}

export const deleteLike = (id) => (dispatch) => {
    axios.delete("http://localhost:8080/interest/"+id)
        .then(dispatch(getAnimals(false, true)))
        .catch((e) => {
            if(e.response.status === 404) {
                alert("Musisz najpierw być zainteresowany zwierzęciem!")
            } else {
                alert("Wystąpił nieoczekiwany błąd!")
            }
        })
}

export const redirectRole = () => (dispatch) => {
    axios.get("http://localhost:8080/getUserRole")
        .then((res) => {
            dispatch(setRole(res.data))
            if (res.data === "ADMIN") {
                window.location.replace("/admin")
            }
            else if (res.data === "EMPLOYEE") {
                window.location.replace("/employee")
            }
            else {
                window.location.replace("/")
            }
        })
        .catch((e) => {
            dispatch(setLogin(false));
            window.location.replace("/");
        })
}



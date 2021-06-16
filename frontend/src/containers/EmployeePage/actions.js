import {ActionTypes} from "./constants";
import axios from "axios";
import {getAnimals} from "../HomePage/actions";
import {setRole} from "../LoginPage/actions";


export function setBreeds(breeds) {
    return {
        type: ActionTypes.SET_BREEDS,
        breeds
    }
}

export function setSizes(sizes) {
    return {
        type: ActionTypes.SET_SIZES,
        sizes
    }
}

export function setAddAnimalError(error) {
    return {
        type: ActionTypes.SET_ADD_ERROR,
        error
    }
}

export const getBreeds = () => (dispatch) => {
    axios.get("http://localhost:8080/breed")
        .then((response) => {
            dispatch(setBreeds(response.data))
        })
        .catch((e) => alert("Wystąpił błąd podczas ładowania ras zwierząt"))
}

export const getSizes = () => (dispatch) => {
    axios.get("http://localhost:8080/size")
        .then((response) => {
            dispatch(setSizes(response.data))
        })
        .catch((e) => alert("Wystąpił błąd podczas ładowania rozmiarów zwierząt"))
}

export const isUserEmployee = () => (dispatch) => {
    axios.get("http://localhost:8080/getUserRole")
        .then((res) => {
            if (res.data === "EMPLOYEE") {
                dispatch(setRole(res.data));
            } else {
                window.location.replace("/")
            }
        })
        .catch((e) => {
            alert("Błąd z autentykacją");
            dispatch(setRole("UNLOGGED"));
            window.location.replace("/");
        })
}

export const addAnimal = (event) => (dispatch) => {
    event.preventDefault();
    var name = event.target.animalName.value;
    var arrivalDate = event.target.arrivalDate.value;
    var size = event.target.size.value;
    var breed = event.target.breed.value;
    var description = event.target.description.value;
    axios.post("http://localhost:8080/animal", {
        name: name,
        arrivalDate: arrivalDate,
        description: description,
        breed: {idBreed: breed},
        size: {idSize: size}
    })
        .then((response) => {
            dispatch(getAnimals());
            dispatch(setAddAnimalError(""));
            alert("Pomyślnie dodano zwierzę")
        })
        .catch((e) => alert("Wystąpił błąd! Nie udało się dodać zwierzęcia"))
}

export const deleteAnimal = (id) => (dispatch) => {
    axios.delete("http://localhost:8080/animal/"+id)
        .then((response) => {
            dispatch(getAnimals());
            window.location.replace("/employee")
            alert("Pomyślnie usunięto zwierzę");
        })
        .catch((e) => alert("Wystąpił błąd podczas usuwania zwierzęcia"))
}

export const setError = (error) => (dispatch) => {
    dispatch(setAddAnimalError(error))
}


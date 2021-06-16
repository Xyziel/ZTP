import axios from "axios";
import {ActionTypes} from "./constants";

export function setRegisterError(error) {
    return {
        type: ActionTypes.SET_REGISTER_ERROR,
        error
    }
}

export const register = (event) => (dispatch) => {
    event.preventDefault();
    const email = event.target.email.value;
    const password = event.target.password.value;
    console.log(email);
    console.log(password);
    axios.post("http://localhost:8080/register", {email: email, password: password})
        .then((res) => {
            alert("Rejestracja przebiegla pomyślnie. Możesz się teraz zalogować");
            window.location.replace("/login");
        })
        .catch((e) =>{
            if(e.response.status === 409) {
                dispatch(setRegisterError("Podany email juz istnieje"))
            }
            else {
                dispatch(setRegisterError("Wystapil nieoczekiwany blad"))
            }
        });
};

export const setError = (error) => (dispatch) => {
    dispatch(setRegisterError(error))
}
import {ActionTypes} from "./constants";
import axios from "axios";

export function setEmail(email) {
    return {
        type: ActionTypes.SET_EMAIL,
        email
    }
}

export function setChangePaswordError(error) {
    return {
        type: ActionTypes.SET_CHANGE_PASS_ERROR,
        error
    }
}

export const checkIfUser = () => (dispatch) => {
    axios.get("http://localhost:8080/getUserRole")
        .then((res) => {
            if (res.data !== "USER") {
                window.location.replace("/");
            }
        })
        .catch((e) => {
            alert("Wystąpił nieoczekiwany błąd");
            window.location.replace("/");
        })
}

export const getUserEmail = () => (dispatch) => {
    axios.get("http://localhost:8080/getUserEmail")
        .then((response) => {
            dispatch(setEmail(response.data));
        })
        .catch((e) => {
            dispatch(setEmail("Błąd podczas ładowania emaila."));
        })
}

export const changePassword = (password, newPassword, repeatedNewPassword, closePopup) => (dispatch) => {
    axios.put("http://localhost:8080/user/changePassword", {oldPassword: password,
                                                                newPassword: newPassword,
                                                                repeatedPassword: repeatedNewPassword})
        .then((response) => {
            alert("Twoje hasło zostało pomyślnie zmienone");
            closePopup();
        })
        .catch((e) => {
            if(e.response.status === 406) {
                dispatch(setChangePaswordError("Podane hasło nie jest prawidłowe"))
            } else {
                dispatch(setChangePaswordError("Wystąpił błąd! Nie udało się zmienić hasła"))
            }
        })
}

export const setError = (error) => (dispatch) => {
    dispatch(setChangePaswordError(error))
}
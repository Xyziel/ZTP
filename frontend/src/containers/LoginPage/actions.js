import {ActionTypes} from "./constants";
import axios from 'axios';
import {getUserInterests, redirectRole} from "../HomePage/actions";

export function setLogin(isLoggedIn) {
    return {
        type: ActionTypes.SET_LOGIN,
        isLoggedIn
    }
}

export function setRole(role) {
    return {
        type: ActionTypes.SET_ROLE,
        role
    }
}

export function setLoginError(error) {
    return {
        type: ActionTypes.SET_LOGIN_ERROR,
        error
    }
}

export const login = (event) => (dispatch) => {
    event.preventDefault();
    const email = event.target.email.value;
    const password = event.target.password.value;
    axios.post("http://localhost:8080/login", {email: email, password: password})
        .then((res) => {
            dispatch(setLogin(true));
            dispatch(redirectRole())
        })
        .catch((e) => {
            if(e.response.status === 401) {
                dispatch(setLoginError("Zły login lub hasło"))
            }
            else {
                dispatch(setLoginError("Wystapil nieoczekiwany blad"))
            }
        })
};



export const isUserLoggedIn = () => (dispatch) => {
    axios.get("http://localhost:8080/getUserRole")
        .then((res) => {
            if (res.data === "UNLOGGED") {
                dispatch(setLogin(false));
            } else {
                dispatch(setLogin(true));
                dispatch(setRole(res.data));
                if(res.data === "USER") {
                    dispatch(getUserInterests(false, true));
                }
            }
        })
        .catch((e) => {
            dispatch(setLogin(false));
        })
};

export const logout = (setLogin) => dispatch => {
    axios.post("http://localhost:8080/logout")
        .then((res) => {
            dispatch(setLogin(false));
            window.location.replace("/login");
        })
        .catch((e) => alert("Wystąpił błąd podczas wylogowywania"))
};
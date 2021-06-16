import axios from "axios";
import {ActionTypes} from "./constants";
import {setRole} from "../LoginPage/actions";

export function setUsers(users) {
    return {
        type: ActionTypes.SET_USERS,
        users
    }
}

export function setAddEmployeeError(error) {
    return {
        type: ActionTypes.SET_ADD_EMPLOYEE_ERROR,
        error
    }
}

export const getUsers = () => (dispatch) => {
    axios.get("http://localhost:8080/user/getAllUsersWithoutAdmin")
        .then((response) => dispatch(setUsers(response.data)))
        .catch((e) => alert("Nie udało się załadować użytkowników"))
}

export const deleteUser = (id) => (dispatch) => {
    axios.delete("http://localhost:8080/user/"+id)
        .then((response) => dispatch(getUsers()))
        .catch((e) => alert("Nie udało się usunąć użytkownika o id: " + id))
}

export const isUserAdmin = () => (dispatch) => {
    axios.get("http://localhost:8080/getUserRole")
        .then((res) => {
            if (res.data === "ADMIN") {
                dispatch(setRole(res.data));
            } else {
                window.location.replace("/")
            }
        })
        .catch((e) => {
            alert("Błąd authentykacji");
            dispatch(setRole("UNLOGGED"));
            window.location.replace("/")
        })
}

export const addEmployee = (email, password) => (dispatch) => {
    axios.post("http://localhost:8080/user/addEmployee", {email: email, password: password})
        .then((response) => {
            alert("Pomyślnie dodano pracownika");
            dispatch(getUsers());
            dispatch(setError(""));
        })
        .catch((e) => {
            if(e.response.status === 409) {
                dispatch(setError("Podany email juz istnieje"));
            }
            else {
                dispatch(setError("Wystapil nieoczekiwany blad podczas dodawania pracownika"));
            }
        })
}

export const setError = (error) => (dispatch) => {
    dispatch(setAddEmployeeError(error));
}
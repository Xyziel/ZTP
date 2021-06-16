import {ActionTypes} from "./constants";

const defaultState = {
    isLoggedIn: false,
    role: "UNLOGGED",
    loginError: "",
};

export default function loginPageReducer(state = defaultState, action) {
    switch (action.type) {
        case ActionTypes.SET_LOGIN:
            return {
                ...state,
                isLoggedIn: action.isLoggedIn
            };
        case ActionTypes.SET_ROLE:
            return {
                ...state,
                role: action.role
            }
        case ActionTypes.SET_LOGIN_ERROR:
            return {
                ...state,
                loginError: action.error
            }
        default:
            return state;
    }
}


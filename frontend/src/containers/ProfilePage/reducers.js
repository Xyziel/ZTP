import {ActionTypes} from "./constants";

const defaultState = {
    email: "",
    changePasswordError: "",
}

export default function profilePageReducer(state = defaultState, action) {
    switch (action.type) {
        case ActionTypes.SET_EMAIL:
            return {
                ...state,
                email: action.email
            }
        case ActionTypes.SET_CHANGE_PASS_ERROR:
            return {
                ...state,
                changePasswordError: action.error
            }
        default:
            return state;
    }
}

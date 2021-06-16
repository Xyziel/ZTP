import {ActionTypes} from "./constants";

const defaultState = {
    registerError: "",
};

export default function registerPageReducer(state = defaultState, action) {
    switch (action.type) {
        case ActionTypes.SET_REGISTER_ERROR:
            return {
                ...state,
                registerError: action.error
            }
        default:
            return state;
    }
}
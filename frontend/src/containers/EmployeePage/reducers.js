import {ActionTypes} from "./constants";

const defaultState = {
    breeds: [],
    sizes: [],
    addAnimalError: ""
};

export default function employeePageReducer(state = defaultState, action) {
    switch (action.type) {
        case ActionTypes.SET_BREEDS:
            return {
                ...state,
                breeds: action.breeds
            };
        case ActionTypes.SET_SIZES:
            return {
                ...state,
                sizes: action.sizes
            }
        case ActionTypes.SET_ADD_ERROR:
            return {
                ...state,
                addAnimalError: action.error
            }
        default:
            return state;
    }
}
import {ActionTypes} from "./constants";

const defaultState = {
    animal: {
        idAnimal: null,
        name: null,
        arrivalDate: null,
        description: null,
        breed: {
            name: null
        },
        size: {
            name: null
        }
    },
    profileLikes: 0
};

export default function animalPageReducer(state = defaultState, action) {
    switch (action.type) {
        case ActionTypes.SET_ANIMAL:
            return {
                ...state,
                animal: action.animal
            };
        case ActionTypes.SET_ANIMAL_LIKES:
            return {
                ...state,
                profileLikes: action.likes
            }
        default:
            return state;
    }
}
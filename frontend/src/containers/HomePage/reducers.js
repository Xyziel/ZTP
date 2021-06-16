import {ActionTypes} from "./constants";

const defaultState = {
    animals: [],
    recentAnimals: [],
    likes: {},
    userInterests: [],
};

export default function homePageReducer(state = defaultState, action) {
    switch (action.type) {
        case ActionTypes.SET_ANIMALS:
            return {
                ...state,
                animals: action.animals
            };
        case ActionTypes.SET_RECENT_ANIMALS:
            return  {
                ...state,
                recentAnimals: action.recentAnimals
            };
        case ActionTypes.SET_LIKES:
            return {
                ...state,
                likes: {
                    ...state.likes, [action.id]: action.likesById}
            };
        case ActionTypes.SET_USER_INTERESTS:
            return {
                ...state,
                userInterests: action.ids
            }
        default:
            return state;
    }
}
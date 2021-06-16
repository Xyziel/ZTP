import {ActionTypes} from "./constants";

const defaultState = {
    users: [],
    admin: false,
    addEmployeeError: "",
};

export default function homePageReducer(state = defaultState, action) {
    switch (action.type) {
        case ActionTypes.SET_USERS:
            return {
                ...state,
                users: action.users
            };
        case ActionTypes.SET_ADMIN:
            return {
                ...state,
                admin: action.isAdmin
            }
        case ActionTypes.SET_ADD_EMPLOYEE_ERROR:
            return {
                ...state,
                addEmployeeError: action.error
            }
        default:
            return state;
    }
}
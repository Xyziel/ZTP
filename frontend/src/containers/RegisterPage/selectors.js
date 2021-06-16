import {createSelector} from "reselect"

const registerPageState = (state) => state.registerPage;

export const makeSelectResponse = createSelector(
    registerPageState,
    (registerPage) => ({...registerPage}),
);
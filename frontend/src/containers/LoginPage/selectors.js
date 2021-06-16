import {createSelector} from "reselect"

const loginPageState = (state) => state.loginPage;

export const makeSelectResponse = createSelector(
    loginPageState,
    (loginPage) => ({...loginPage}),

);

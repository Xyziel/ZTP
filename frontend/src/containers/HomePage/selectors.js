import {createSelector} from "reselect"

const homePageState = (state) => state.homePage;
const loginPageState = (state) => state.loginPage;

export const makeSelectResponse = createSelector(
    homePageState,
    loginPageState,
    (homePage, loginPage) => ({...homePage, ...loginPage})
);

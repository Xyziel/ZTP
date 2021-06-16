import {createSelector} from "reselect"

const adminPageState = (state) => state.adminPage;
const loginPageState = (state) => state.loginPage;
const homePageState = (state) => state.homePage;

export const makeSelectResponse = createSelector(
    adminPageState,
    loginPageState,
    homePageState,
    (adminPage, loginPage, homePage) => ({...adminPage, ...loginPage, ...homePage})
);

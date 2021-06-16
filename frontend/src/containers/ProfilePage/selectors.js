import {createSelector} from "reselect"

const profilePageState = (state) => state.profilePage;
const homePageState = (state) => state.homePage;
const loginPageState = (state) => state.loginPage;

export const makeSelectResponse = createSelector(
    profilePageState,
    homePageState,
    loginPageState,
    (profilePage, homePage, loginPage) => ({...profilePage, ...homePage, ...loginPage}),

);

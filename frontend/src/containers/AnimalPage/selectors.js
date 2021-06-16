import {createSelector} from "reselect"

const animalPageState = (state) => state.animalPage;
const homePageState = (state) => state.homePage;
const loginPageState = (state) => state.loginPage;
const employeePageState = (state) => state.employeePage

export const makeSelectResponse = createSelector(
    animalPageState,
    homePageState,
    loginPageState,
    employeePageState,
    (animalPage, homePage, loginPage, employeePage) => ({...animalPage, ...homePage, ...loginPage, ...employeePage})
);

import {createSelector} from "reselect"

const employeePageState = (state) => state.employeePage;
const homePageState = (state) => state.homePage;
const loginPageState = (state) => state.loginPage;

export const makeSelectResponse = createSelector(
    employeePageState,
    homePageState,
    loginPageState,
    (employeePage, homePage, loginPage) => ({...employeePage, ...homePage, ...loginPage})
);

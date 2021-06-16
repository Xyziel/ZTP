import {createStore, combineReducers, applyMiddleware} from "redux";
import homePage from "./containers/HomePage/reducers";
import loginPage from "./containers/LoginPage/reducers";
import animalPage from "./containers/AnimalPage/reducers"
import registerPage from "./containers/RegisterPage/reducers";
import adminPage from "./containers/AdminPage/reducers"
import employeePage from "./containers/EmployeePage/reducers"
import profilePage from "./containers/ProfilePage/reducers"
import thunk from "redux-thunk";

const reducers = combineReducers({homePage, loginPage, animalPage, registerPage, adminPage, employeePage, profilePage});

export default createStore(reducers, applyMiddleware(thunk));
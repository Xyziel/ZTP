import React from "react";
import './App.css';
import {Route, BrowserRouter as Router, Switch} from "react-router-dom";
import HomePage from "./containers/HomePage";
import AnimalPage from "./containers/AnimalPage";
import LoginPage from "./containers/LoginPage";
import RegisterPage from "./containers/RegisterPage";
import ProfilePage from "./containers/ProfilePage";
import AdminPage from "./containers/AdminPage";
import EmployeePage from "./containers/EmployeePage";
import axios from "axios";

axios.defaults.withCredentials = true;
axios.defaults.headers['Content-type'] = 'application/json';

function App() {
    return (
        <div className="App">
            <Router>
                <Switch>
                    <Route exact path="/" component={HomePage}/>
                    <Route exact path="/animal/:id" component={AnimalPage}/>
                    <Route exact path="/login" component={LoginPage}/>
                    <Route exact path="/registration" component={RegisterPage}/>
                    <Route exact path="/profile" component={ProfilePage}/>
                    <Route exact path="/admin" component={AdminPage}/>
                    <Route exact path="/employee" component={EmployeePage}/>
                    <Route>404 Not Found!</Route>
                </Switch>
            </Router>
        </div>
    );
}

export default App;

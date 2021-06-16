import React, {Component} from "react";
import {Form, Button, Container} from "react-bootstrap";
import {NavigationBar} from "../../components/Navigationbar";
import "../../styles/css/loginPage.css";
import {connect} from "react-redux";
import {makeSelectResponse} from "./selectors";
import {login} from "./actions";

class LoginPage extends Component{

    handleLogin = (event) => {
        this.props.login(event);
    };

    render() {
        return (
            <Container>
                <NavigationBar/>
                <Form className="d-flex flex-column m-auto mt-5 w-30" onSubmit={this.handleLogin}>
                    <Form.Group className="justify-content-start mt-2" controlId="formBasicEmail">
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" name="email" placeholder="Wpisz email"/>
                    </Form.Group>
                    <Form.Group className="justify-content-start my-4" controlId="formBasicPassword">
                        <Form.Label>Hasło</Form.Label>
                        <Form.Control type="password" name="password" placeholder="Wpisz hasło"/>
                    </Form.Group>
                    <p className="mb-2" style={{color:"red"}}>{this.props.loginError}</p>
                    <Button className="m-auto w-30 tx-cnt" variant="outline-secondary" type="submit">
                        Zaloguj
                    </Button>
                    <a className="mt-2 tx-cnt registrationLink" href="/registration">Nie posiadasz konta? Zarejestruj
                        się!</a>
                </Form>
            </Container>
        );
    }

}

const mapDispatchToProps = {
    login,
};


export default connect(makeSelectResponse, mapDispatchToProps)(LoginPage);
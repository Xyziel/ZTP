import React, {Component} from "react";
import {NavigationBar} from "../../components/Navigationbar";
import {Button, Container, Form} from "react-bootstrap";
import {register, setError} from "./actions";
import {connect} from "react-redux";
import {makeSelectResponse} from "./selectors";

class RegisterPage extends Component {

    handleRegister = (event) => {
        event.preventDefault();
        const password = event.target.password.value;
        const repeatedPassword = event.target.repeatedPassword.value;
        if (password === repeatedPassword) {
            this.props.register(event);
        } else {
            this.props.setError("Hasła nie są identyczne")
        }
    };

    render() {
        return (
            <Container>
                <NavigationBar/>
                <Form className="d-flex flex-column m-auto mt-5 w-30" onSubmit={this.handleRegister}>
                    <Form.Group className="justify-content-start mt-2">
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" name="email" placeholder="Wpisz email" />
                    </Form.Group>
                    <Form.Group className="justify-content-start mt-4">
                        <Form.Label>Hasło</Form.Label>
                        <Form.Control type="password" name="password" placeholder="Wpisz hasło" />
                    </Form.Group>
                    <Form.Group className="justify-content-start my-4">
                        <Form.Label>Powtórz hasło</Form.Label>
                        <Form.Control type="password" name="repeatedPassword" placeholder="Wpisz hasło" />
                    </Form.Group>
                    <p className="mb-2" style={{color:"red"}}>{this.props.registerError}</p>
                    <Button className="m-auto w-40 tx-cnt" variant="outline-secondary" type="submit">
                        Zarejestruj się
                    </Button>
                </Form>
            </Container>
        );
    }
}

const mapDispatchToProps = {
    register,
    setError,
};

export default connect(makeSelectResponse, mapDispatchToProps)(RegisterPage);
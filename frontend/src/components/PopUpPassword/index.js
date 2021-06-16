import React from "react";
import "../../styles/css/popup.css"
import {Button, Form} from "react-bootstrap";

export function PopUpPassword({closePopup, changePasswordFunc, changePasswordError, setErrorFunc}) {

    const handleChangePassword = (event) => {
        event.preventDefault();
        var password = event.target.password.value;
        var newPassword = event.target.newPassword.value;
        var newRepeatedPassword = event.target.newRepeatedPassword.value;

        if(password === newPassword) {
            setErrorFunc("Stare i nowe hasło musi być inne")
        } else if(newPassword !== newRepeatedPassword) {
            setErrorFunc("Nowe hasła nie są identyczne")
        }
        else {
            changePasswordFunc(password, newPassword, newRepeatedPassword, closePopup);
        }

    }

    return (
        <div className='popup'>
            <div className='popupInner'>
                <button className="close" onClick={closePopup}>&times;</button>
                <Form className="d-flex flex-column m-auto px-4" onSubmit={handleChangePassword}>
                    <Form.Group className="justify-content-start mt-2">
                        <Form.Label>Obecne hasło</Form.Label>
                        <Form.Control type="password" name="password" placeholder="Wpisz obecne hasło" />
                    </Form.Group>
                    <Form.Group className="justify-content-start mt-4">
                        <Form.Label>Nowe hasło</Form.Label>
                        <Form.Control type="password" name="newPassword" placeholder="Wpisz nowe hasło" />
                    </Form.Group>
                    <Form.Group className="justify-content-start my-4">
                        <Form.Label>Powtórz nowe hasło</Form.Label>
                        <Form.Control type="password" name="newRepeatedPassword" placeholder="Wpisz nowe hasło" />
                    </Form.Group>
                    <p className="mb-2" style={{color:"red"}}>{changePasswordError}</p>
                    <Button className="m-auto tx-cnt mb-3" variant="outline-secondary" type="submit">
                        Zmień
                    </Button>
                </Form>
            </div>
        </div>
    );
}
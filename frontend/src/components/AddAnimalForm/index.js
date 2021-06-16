import React from "react";
import {Button, Col, Form, Row} from "react-bootstrap";

export function AddAnimalForm({
                                  label = "Formularz zwierzaka",
                                  btnText = "Dodaj",
                                  infoSize = '1',
                                  breeds,
                                  addAnimalFunc,
                                  sizes,
                                  addAnimalError,
                                  setError
                              }) {

    const handleAddAnimal = (event) => {
        event.preventDefault();
        var name = event.target.animalName.value;
        var arrivalDate = event.target.arrivalDate.value;
        var description = event.target.description.value;
        if(name === "") {
            setError("Wpisz imie");
        } else if(arrivalDate === "" || Date.now() < Date.parse(arrivalDate)) {
            setError("Niepoprawna data");
        } else if (description === "") {
            setError("Napisz coś o zwierzaku");
        } else {
            addAnimalFunc(event);
        }
    }

    return (
        <div>
            <p className="addLabel ms-5 mt-2">{label}</p>
            <Form data-testid="addAnimalForm" onSubmit={handleAddAnimal}>
                <Form.Group as={Row} className="m-2">
                    <Form.Label column sm={infoSize}>Imię:</Form.Label>
                    <Col sm="3">
                        <Form.Control type="text" data-testid="formName" name="animalName" placeholder="Wpisz imię"/>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} className="m-2">
                    <Form.Label column sm={infoSize}>Data:</Form.Label>
                    <Col sm="3">
                        <Form.Control type="date" data-testid="formDate" name="arrivalDate"/>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} className="m-2">
                    <Form.Label column sm={infoSize}>Rozmiar:</Form.Label>
                    <Col sm="3">
                        <Form.Control as="select" name="size" data-testid="formSize">
                            {
                                sizes.map((size, index) =>
                                    <option value={index + 1}>{size.name}</option>)
                            }
                        </Form.Control>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} className="m-2">
                    <Form.Label column sm={infoSize}>Rasa:</Form.Label>
                    <Col sm="3">
                        <Form.Control as="select" name="breed" data-testid="formBreed">
                            {
                                breeds.map((breed, index) =>
                                    <option value={index + 1}>{breed.name}</option>)
                            }
                        </Form.Control>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} className="m-2">
                    <Form.Label column sm={infoSize}>Opis:</Form.Label>
                    <Col sm="7">
                        <Form.Control as="textarea" data-testid="formDescription" name="description" rows={5} placeholder="Opis zwierzaka"/>
                        <p className="mt-1" style={{color: "red"}}>{addAnimalError}</p>
                    </Col>
                </Form.Group>
                <Col xs="5" className="tx-cnt">
                    <Button className="ms-5" variant="outline-dark" type="submit">{btnText}</Button>
                </Col>
            </Form>
        </div>
    )
}
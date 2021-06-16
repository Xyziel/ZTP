import React from "react";
import {Button, Form, Table} from "react-bootstrap";
import "../../styles/css/usersTable.css";

export function UserTable({users, deleteUserFunc, addEmployeeFunc, addEmployeeError, setErrorFunc}) {

    const handleAddEmployee = (event) => {
        event.preventDefault();
        const repeatedPassword = event.target.repeatedPassword.value;
        const password = event.target.password.value;
        const email = event.target.email.value;
        if(repeatedPassword === "" || password === "" || email === "") {
            setErrorFunc("Uzupełnij wszystkie pola formularza");
        } else if(repeatedPassword !== password) {
            setErrorFunc("Hasła muszą być identyczne");
        } else {
            addEmployeeFunc(email, password);
        }
    }

    return (
        <div className="d-flex flex-row">
            <div className="tableContainer">
                <Table striped bordered hover className="usersTable">
                    <thead>
                        <tr className="tableNames">
                            <th className="id">ID</th>
                            <th className="id">ROLA</th>
                            <th>Email</th>
                            <th className="action">Akcja</th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        users.map((user) =>
                            <tr key={user.idUser}>
                                <th className="idColumn">{user.idUser}</th>
                                <th className="role">{user.role.name}</th>
                                <td>{user.email}</td>
                                <td className="actionColumn"><Button className="deleteButton" variant="outline-danger" onClick={() => deleteUserFunc(user.idUser)}>Usuń</Button></td>
                            </tr>
                        )
                    }
                    </tbody>
                </Table>
            </div>
            <div className="addEmployeeContainer">
                <p className="addLabel">Dodaj pracownika</p>
                <Form className="d-flex flex-column m-auto mt-1 w-40 tx-lf" onSubmit={handleAddEmployee}>
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
                    <p className="mb-2" style={{color:"red"}}>{addEmployeeError}</p>
                    <Button className="m-auto w-40 tx-cnt" variant="outline-info" type="submit">
                        Dodaj
                    </Button>
                </Form>
            </div>
        </div>
    );
}
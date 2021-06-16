import React from "react";
import {Navbar, Nav, Button} from "react-bootstrap";
import "../../styles/css/navbar.css"
import {MainLabel} from "../MainLabel";

export function NavigationBar({isLoggedIn, role, logoutFunc}) {
    const classesHomeLabel = (role === "ADMIN") ? "nav-link invisible" : "nav-link";
    return (
        <Navbar className="bg-lighter navbar-dark justify-content-between" data-testid="navbar">
            <Nav className="ms-1">
                <Nav.Item>
                    <a href={"/"} className={classesHomeLabel} id="homeLabel">Strona główna</a>
                </Nav.Item>
            </Nav>
            <Nav>
                <Nav.Item>
                    <MainLabel/>
                </Nav.Item>
            </Nav>
            <Nav className="me-3">
                <Nav.Item>
                    {isLoggedIn && (role === "USER") ? <Button variant="outline-light w-103p me-2" href="/profile"
                                                               className="profileIconNavbar"
                                                               data-testid="profileButton">Profile</Button> : null}
                    {isLoggedIn ? <Button variant="outline-light w-103p" data-testid="loginButton"
                                          onClick={() => logoutFunc()}>Wyloguj</Button>
                        : <Button variant="outline-light w-103p" href="/login" data-testid="loginButton">Logowanie</Button>}
                </Nav.Item>
            </Nav>
        </Navbar>
    );

}
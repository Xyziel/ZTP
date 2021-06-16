import React, {Component} from "react";
import {Button, Container} from "react-bootstrap";
import {NavigationBar} from "../../components/Navigationbar";
import {faUser} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import "../../styles/css/profilePage.css"
import {AnimalCard} from "../../components/AnimalCard";
import {PopUpPassword} from "../../components/PopUpPassword";
import {isUserLoggedIn, logout, setLogin} from "../LoginPage/actions";
import {connect} from "react-redux";
import {makeSelectResponse} from "./selectors";
import {deleteLike, getAnimals, getUserInterests} from "../HomePage/actions";
import {changePassword, checkIfUser, getUserEmail} from "./actions";
import {setError} from "./actions";


class ProfilePage extends Component {

    constructor(props) {
        super(props);
        this.props.isUserLoggedIn();
        this.props.checkIfUser()
        this.props.getUserEmail();
        this.state = {
            showPopupPassword: false,
        };
    }


    componentDidMount() {
        this.props.getAnimals(true);
    }

    togglePopup() {
        this.setState({
            showPopupPassword: !this.state.showPopupPassword
        });
    }

    getUserAnimals() {
        var userAnimals = [];
        this.props.animals.forEach((animal) => {
                if (this.props.userInterests.includes(animal.idAnimal)) {
                    userAnimals.push(<AnimalCard key={animal.idAnimal}
                                                 animal={animal}
                                                 isInterested={true}
                                                 persons={this.props.likes[animal.idAnimal]}
                                                 deleteInterestFunc={this.props.deleteLike.bind(this)}
                                                 isLoggedIn={this.props.isLoggedIn}
                                                 role={"USER"}/>)
                }
            }
        )
        return <div className="animalsCards">
            {userAnimals.map((animal) => animal)}
        </div>
    }

    render() {
        return (
            <Container>
                {
                    this.props.role === "USER" ?
                        <div>
                            <NavigationBar isLoggedIn={this.props.isLoggedIn}
                                           logoutFunc={this.props.logout.bind(this, setLogin)}
                                           role={this.props.role}/>
                            <div
                                className="d-flex flex-row justify-content-center align-content-center w-40 m-auto mt-5">
                                <FontAwesomeIcon icon={faUser} className="profileIcon"/>
                                <div className="d-flex flex-column justify-content-center ms-5">
                                    <p>Email: {this.props.email}</p>
                                    <Button className="mt-3" variant="outline-dark" onClick={() => this.togglePopup(1)}>Zmień
                                        hasło</Button>
                                </div>
                            </div>
                            <div>
                                <p className="animalContainerLabel mt-5">Zwierzęta, które Cię zainteresowały:</p>
                                {
                                    this.props.userInterests.length > 0 ? this.getUserAnimals() : null
                                }
                            </div>
                            {this.state.showPopupPassword && <PopUpPassword closePopup={this.togglePopup.bind(this)}
                                                                            changePasswordFunc={this.props.changePassword}
                                                                            changePasswordError={this.props.changePasswordError}
                                                                            setErrorFunc={this.props.setError.bind(this)}/>}
                        </div>
                        : null
                }
            </Container>
        )
    }
}

const mapDispatchToProps = {
    getAnimals,
    isUserLoggedIn,
    getUserInterests,
    checkIfUser,
    deleteLike,
    logout,
    getUserEmail,
    changePassword,
    setError,
};


export default connect(makeSelectResponse, mapDispatchToProps)(ProfilePage);
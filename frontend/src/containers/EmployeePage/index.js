import React, {Component} from "react";
import {NavigationBar} from "../../components/Navigationbar";
import {Container} from "react-bootstrap";
import {AddAnimalForm} from "../../components/AddAnimalForm";
import {AnimalsContainer} from "../../components/AnimalsContainer";
import {connect} from "react-redux";
import {makeSelectResponse} from "./selectors";
import {getAnimals} from "../HomePage/actions";
import {isUserLoggedIn, logout, setRole} from "../LoginPage/actions";
import {addAnimal, getBreeds, getSizes, deleteAnimal, isUserEmployee, setError} from "./actions";

class EmployeePage extends Component {

    constructor(props) {
        super(props);
        this.props.isUserEmployee();
    }

    componentDidMount() {
        this.props.getAnimals(true);
        this.props.getBreeds();
        this.props.getSizes();
    }


    render() {
        return (
            <Container>
                {
                    this.props.role === "EMPLOYEE" ?
                        <div>
                            <NavigationBar role={this.props.role}
                                           isLoggedIn={true}
                                           logoutFunc={this.props.logout.bind(this, setRole)}/>
                            <AddAnimalForm breeds={this.props.breeds}
                                           addAnimalFunc={this.props.addAnimal}
                                           sizes={this.props.sizes}
                                           addAnimalError={this.props.addAnimalError}
                                           setError={this.props.setError}/>
                            <AnimalsContainer animals={this.props.animals}
                                            interestedIds={[]}
                                            likes={this.props.likes}
                                            addInterestFunc={null}
                                            deleteInterestFunc={null}
                                            isLoggedIn={true}
                                            role={"EMPLOYEE"}/>
                        </div>
                    : null
                }

            </Container>
        );
    }
}

const mapDispatchToProps = {
    getAnimals,
    isUserLoggedIn,
    getBreeds,
    getSizes,
    addAnimal,
    logout,
    deleteAnimal,
    isUserEmployee,
    setError,
}

export default connect(makeSelectResponse, mapDispatchToProps)(EmployeePage);
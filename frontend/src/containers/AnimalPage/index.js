import React, {Component} from "react";
import {NavigationBar} from "../../components/Navigationbar";
import {AnimalProfile} from "../../components/AnimalProfile";
import {Container} from "react-bootstrap";
import {connect} from "react-redux";
import {getAnimalById, getAnimalLikes, addLikeProfile, deleteLikeProfile} from "./actions";
import {makeSelectResponse} from "./selectors";
import {getUserInterests} from "../HomePage/actions";
import {setLogin, isUserLoggedIn, logout} from "../LoginPage/actions";
import {deleteAnimal, getBreeds, getSizes} from "../EmployeePage/actions";

class AnimalPage extends Component {

    constructor(props) {
        super(props);
        this.props.isUserLoggedIn();
    }

    componentDidMount() {
        this.props.getAnimalById(this.props.match.params.id);
        this.props.getAnimalLikes(this.props.match.params.id)
    }

    render() {
        console.log("aa", this.props)
        return (
            <div>
                <Container>
                    <NavigationBar isLoggedIn={this.props.isLoggedIn}
                                   logoutFunc={this.props.logout.bind(this, setLogin)}
                                   role={this.props.role}/>
                    <AnimalProfile animal={this.props.animal}
                                   persons={this.props.profileLikes}
                                   isInterested={this.props.userInterests.includes(this.props.animal.idAnimal)}
                                   addInterestFunc={this.props.addLikeProfile.bind(this)}
                                   deleteInterestFunc={this.props.deleteLikeProfile.bind(this)}
                                   animalId={this.props.animal.idAnimal}
                                   isLoggedIn={this.props.isLoggedIn}
                                   role={this.props.role}
                                   deleteAnimalFunc={this.props.deleteAnimal.bind(this)}/>
                </Container>
            </div>
        );
    }
}

const mapDispatchToProps = {
    getAnimalById,
    getUserInterests,
    isUserLoggedIn,
    logout,
    getAnimalLikes,
    addLikeProfile,
    deleteLikeProfile,
    getBreeds,
    getSizes,
    deleteAnimal,
};

export default connect(makeSelectResponse, mapDispatchToProps)(AnimalPage);

import React, {Component} from "react";
import {Container} from "react-bootstrap";
import {Slider} from "../../components/Slider";
import {NavigationBar} from "../../components/Navigationbar";
import {AnimalsContainer} from "../../components/AnimalsContainer";
import {connect} from "react-redux";
import {makeSelectResponse} from "./selectors";
import {getAnimals, getRecentAnimals, getUserInterests, addLike, deleteLike, redirectRole} from "./actions";
import {isUserLoggedIn, setLogin, logout} from "../LoginPage/actions";

class HomePage extends Component {

    constructor(props) {
        super(props);
        this.props.isUserLoggedIn();

    }

    componentDidMount() {
        this.forceUpdate()
        this.props.getAnimals(false, this.props.isLoggedIn);
        this.props.getRecentAnimals();

    }

    render() {
        if(this.props.role === "ADMIN") {
            window.location.replace("/admin")
        } else if(this.props.role === "EMPLOYEE") {
            window.location.replace("/employee")
        }
        else {
            return (
                <Container>
                    <NavigationBar isLoggedIn={this.props.isLoggedIn}
                                   logoutFunc={this.props.logout.bind(this, setLogin)}
                                   role={this.props.role}/>
                    <Slider animals={this.props.recentAnimals}
                            interestedIds={this.props.userInterests}
                            likes={this.props.likes}
                            addInterestFunc={this.props.addLike.bind(this)}
                            deleteInterestFunc={this.props.deleteLike.bind(this)}
                            isLoggedIn={this.props.isLoggedIn}/>
                    <AnimalsContainer animals={this.props.animals}
                                      interestedIds={this.props.userInterests}
                                      likes={this.props.likes}
                                      addInterestFunc={this.props.addLike.bind(this)}
                                      deleteInterestFunc={this.props.deleteLike.bind(this)}
                                      isLoggedIn={this.props.isLoggedIn}/>
                </Container>
            );
        }

    }
}

const mapDispatchToProps = {
    getAnimals,
    getRecentAnimals,
    getUserInterests,
    addLike,
    deleteLike,
    isUserLoggedIn,
    logout,
    redirectRole,
};

export default connect(makeSelectResponse, mapDispatchToProps)(HomePage);

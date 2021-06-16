import React, {Component} from "react";
import {NavigationBar} from "../../components/Navigationbar";
import {Container} from "react-bootstrap";
import {UserTable} from "../../components/UsersTable";
import {connect} from "react-redux";
import {makeSelectResponse} from "./selectors";
import {getUsers, isUserAdmin, deleteUser, addEmployee, setError} from "./actions";
import {setRole, logout} from "../LoginPage/actions";

class AdminPage extends Component {

    constructor(props) {
        super(props);
        this.props.isUserAdmin(this.props);
    }

    componentDidMount() {
        this.props.getUsers();
    }

    render() {
        return (
            <Container>
                {
                    this.props.role === "ADMIN" ?
                        <div>
                            <NavigationBar role={this.props.role} isLoggedIn={true} logoutFunc={this.props.logout.bind(this, setRole)}/>
                            <UserTable users={this.props.users}
                                       deleteUserFunc={this.props.deleteUser}
                                        addEmployeeFunc={this.props.addEmployee}
                                        addEmployeeError={this.props.addEmployeeError}
                                        setErrorFunc={this.props.setError}/>
                        </div> : null
                }
            </Container>

        )

    }
}

const mapDispatchToProps = {
    getUsers,
    deleteUser,
    isUserAdmin,
    logout,
    addEmployee,
    setError,
}

export default connect(makeSelectResponse, mapDispatchToProps)(AdminPage);